package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AuthVO;
import com.company.domain.ChangeVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j // = Log4j2 와 동일 왜 이걸로 바꿨냐면 아무 이유 없음
@RequestMapping("/member/*")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	
	//회원가입 담당
	
	@RequestMapping("/step1") //http://localhost:8087/member/step1 으로 경로 만듬
	public void step1Get() {
		//index에서 회원가입 버튼을 누르면 step1.jsp화면 보여주기
		log.info("step1 페이지 요청");		
	}
	
	//step 2 보여주는 컨트롤러 생성
	@PostMapping("/step2")
	public String step2Get(boolean agree, RedirectAttributes rttr) { //나는 string하고 null값으로 함
		log.info("step2 회원가입 페이지 요청 || " + agree);
		
		//사용자가 체크한 값이 없다면 step1 되돌려보내기 - 에서 return으로 값을 바꿈 원래 void
		if(!agree) {
			rttr.addFlashAttribute("check", "false"); //잠깐 쓰고 말것이라면 flash 사용
			return "redirect:/member/step1";
			//return 값이 redirect:/member/step1일 경우 주소가 step1으로 됨 더 깔끔함
			//return 값이 /member/step1일 때는 주소가 step2로 바뀐 후 step1으로
			//다시 돌아올 때 주소가 안바뀜 방식이 forward라서 
			
		}
		return "/member/step2";
	}
	
	@PostMapping("/step3")
	public String step3Post(@ModelAttribute("regist")RegisterVO regist) {
		//step2.jsp에서 넘긴 값 가져오기
		//로그 출력
		log.info("step3 회원가입 내용 출력 ");
		//되면 step3.jsp로 이동
		if(regist.isPasswordEqualToConfirmPassword()) {
			service.register(regist);
			return "/member/step3";
		} else {
			return "/member/step2";
		}
	}
	
	//step2,step3를 직접적으로 요청했을 때 http://local:8087/member/step2 or step3
	//get으로 요청하는 핸들러
	@GetMapping(value= {"/step2","/step3"})
	public String handleStep2_3() {
		log.info("/step2, / step3 직접 요청");
		return "redirect:step1";
	}
	
	//중복 아이디 확인
	@ResponseBody //보내는 리턴 값은 실제 값임
	@PostMapping("/checkId")
	public String checkId(String userid) { 
	//여기서 String은 사용 안됨 return값을 true나 false로 못잡기 때문에
	//return 값은 주소값, true나 false로는 잡으면 안됨 - 이때 @ResponseBody를 쓰면 사용가능
		log.info("중복 아이디 검사 요청.."+userid);
		RegisterVO dupId = service.selectById(userid);
		if(dupId!=null) {
			log.info("사용못함");
			return "false";
		}
		return "true";
	}
	
	//로그인 - signin보여주기
	@GetMapping("/signin")
	public void signIn() {
		log.info("로그인 페이지 요청");
	}
	
	//아이디,비밀번호를 받아오기
	@PostMapping("/login")
	public String getLoginInfo(LoginVO login, HttpSession session) {
		log.info("로그인 정보 요청");
		AuthVO auth = service.isLogin(login);
		if(auth!=null) {
			session.setAttribute("auth", auth);
			return "redirect:/"; //처음 페이지
		} else {
			//userid, password가 틀려서 로그인을 못한 경우
			return "redirect:signin";
		}
	}
	
	//로그아웃 - 세션해제 후 index 이동
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃");
		//session.invalidate(); //모든 세션 삭제
		session.removeAttribute("auth"); //우리가 담은 로그인 정보만 삭제(특정세션삭제)
		return "redirect:/"; //index로 이동하되 auth가 없는 index로
	}
	
	//회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원 탈퇴 페이지 요청");
	}
	
	//회원탈퇴 - 회원 삭제 후 세션 해제 후 index 이동
	@PostMapping("/leave")
	public String leavePost(LoginVO login, HttpSession session) {
		log.info("회원탈퇴" +login);
		if(service.leaveMember(login)) {
			session.removeAttribute("auth");
			return "redirect:/";			
		} else { //비밀번호가 틀린 경우
			return "redirect:leave";
		}
	}
	
	//회원정보 수정 폼 보여주기
	@GetMapping("/changePwd")
	public void changeInfo() {
		log.info("회원 정보 수정 페이지 요청");
	}
	
	//회원정보 수정 - 현 비밀번호 입력 후 새 비밀번호 입력 및 확인
	@PostMapping("/changePwd")
	public String changePost(ChangeVO change, HttpSession session, RedirectAttributes rttr) {
		//change(password,new_password,cofirm_password)
		log.info("회원정보 수정" + change);
		//userid를 세션에서 가져와서 change에 담기
		AuthVO auth = (AuthVO) session.getAttribute("auth"); //= @SessionAttribute AuthVO auth
		change.setUserid(auth.getUserid());
		
		if(service.updateMember(change)) {
			//성공시 세션 해제 후 로그인 페이지로 이동
			log.info("회원정보 수정 완료");
			session.removeAttribute("auth");
			return "redirect:signin";
		} else {
			rttr.addFlashAttribute("error", "비밀번호를 확인해주세요.");
			return "redirect:/member/changePwd";
		}
	}
}
