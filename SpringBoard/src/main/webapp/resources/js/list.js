/**
 * list.jsp에서 사용하는 스크립트
 */
$(function(){
	//boardController에서 넘긴 값 = rttr.addFlashAttribute
	
	
	checkModal(result);
	
	//history 재지정
	history.replaceState({}, null, null);
	
	//모달 창 띄우기
	function checkModal(result) {
		if(result===''||history.state){
			return;
		}
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 "+result+" 번이 등록되었습니다.");
		}
		$("#myModal").modal("show");
	}
	
	//actionForm 가져오기 - 페이지 이동시 사용할 폼
	var actionForm = $("#actionForm");
	
	//페이지 번호 클릭시 동작
	$(".paginate_button a").click(function(e){
		//a 태그의 기능을 중지
		e.preventDefault(); 
		//pageNum의 값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	})
	
	//amount 값 변경시 동작
	$(".form-control").change(function(){
		//amount 값을 사용자가 선택한 값으로 변경
		actionForm.find("input[name='amount']").val($(this).val());
		actionForm.submit();
	})
	
	//게시판 제목 클릭시 동작(현재 글번호,pageNum,amount,검색정보)
	$(".move").click(function(e){
		//a 태그의 기능을 중지
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr("action","read"); //  /board/read
		actionForm.submit(); 
	})
})