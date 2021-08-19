package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.Dbconn;

public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs = null;
	
	public BoardDao() {
		Dbconn conn = new Dbconn();
		this.conn = conn.getConnect();
	}
	
	public ArrayList<BoardVo> selectAll() {
		
		String sql ="select * from bclass_board";
		ArrayList<BoardVo> alist = new ArrayList();
		System.out.println("Dao class입니다");
		
		try {
				pstmt = conn.prepareStatement(sql);
				System.out.println("prepareStatement(sql);");
				rs = pstmt.executeQuery();
				System.out.println("rs = pstmt.executeQuery();");
				while(rs.next()) {
					BoardVo bv = new BoardVo();
					bv.setBidx(rs.getInt("bidx"));
					bv.setSubject(rs.getString("subject"));
					bv.setWriteday(rs.getString("writeday"));
					alist.add(bv);
				}
		} catch (SQLException e) {
			System.out.println("sql오류야");
			//e.printStackTrace();
		}
		return alist;
	}
	
	public int boardWrite(String subject, String contents, String writer,String ip) {
		String sql = "insert into bclass_board(BIDX,CONTENTS,SUBJECT,WRITER,ip) values(bidx_seq.nextval,?,?,?,?)";
		int result=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
