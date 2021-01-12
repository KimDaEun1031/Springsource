package com.company.presistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

import static com.company.presistence.JDBCUtil.*;

@Repository
public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(MemberVO member) {
		int result = 0;
		
		return result;
	}
	public int update(MemberVO member) {
		int result = 0;
		
		return result;
	}
	public int delete(MemberVO member) {
		int result = 0;
		
		return result;
	}
	public MemberVO getRow(MemberVO member) {
		MemberVO vo = null;
		
		return vo;
	}
	
	public List<MemberVO> getList(){
		List<MemberVO> list = new ArrayList<>();
		try {
			String sql = "select * from member";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setEmail(rs.getString("email"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}
}
