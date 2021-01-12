package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.domain.BoardVO;

import com.company.persistence.JDBCUtil.*;

public class BoardDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public int update(BoardVO vo) {
		int result = 0;
		
		try {
			con = getConnection();
			String sql = "update spring_board set title=?, content=?, updateDate=? where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getBno());
			result = pstmt.executeUpdate();
			if(result>0) {
				commit(con);
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
	public int delete(BoardVO vo) {
		int result = 0;
		
		try {
			String sql = "delete from spring_board where bno=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,vo.getBno());
			result = pstmt.executeUpdate();
			if(result>0) {
				commit(con);
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
	
	public BoardVO getRow(int bno  ) {
		BoardVO vo = null;
		try {
			String sql = "selelct * from spring_board where bno=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
		
	}
	
	
	public List<BoardVO> getList(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			con = getConnection();
			String sql = "select * from spring_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdateDate(rs.getDate("updateDate"));
				
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
