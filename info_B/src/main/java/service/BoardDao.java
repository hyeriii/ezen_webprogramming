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
		
		String sql ="select * from bclass_board where delyn ='N' order by originbidx desc, depth";
		ArrayList<BoardVo> alist = new ArrayList();
		System.out.println("Dao class¿‘¥œ¥Ÿ");
		
		try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					BoardVo bv = new BoardVo();
					bv.setBidx(rs.getInt("bidx"));
					bv.setSubject(rs.getString("subject"));
					bv.setWriteday(rs.getString("writeday"));
					bv.setNlevel(rs.getInt("nlevel"));
					alist.add(bv);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	
	public int boardWrite(String subject, String contents, String writer,String ip,int midx) {
		String sql = "insert into bclass_board(BIDX,ORIGINBIDX,DEPTH,NLEVEL,CONTENTS,SUBJECT,WRITER,ip,midx) values(bidx_seq.nextval,bidx_seq.nextval,0,0,?,?,?,?,?)";
		int result=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			
			result = pstmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public BoardVo boardSelectOne(int bidx) {
		
		BoardVo bv = null;
		String sql ="select viewcount, subject, contents, writer,bidx from bclass_board where bidx=?";
		
		try {
			System.out.println("boardSelectOne(int bidx) ~~~~");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			bv = new BoardVo();
			bv.setViewcount(rs.getInt("viewcount"));
			bv.setSubject(rs.getString("subject"));
			bv.setContents(rs.getString("contents"));
			bv.setWriter(rs.getString("writer"));
			bv.setBidx(rs.getInt("bidx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bv;
	}
	
	//modify 
	public int boardModify(String subject, String contents,String writer, String bidx) {
		int result=0;
		String sql = "update bclass_board set subject=?, contents=?, writer=? where bidx=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			pstmt.setString(3, writer);
			pstmt.setString(4, bidx);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
