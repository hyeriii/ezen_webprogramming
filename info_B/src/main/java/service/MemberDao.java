package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.Dbconn;

public class MemberDao {
	
	private Connection conn;
	private PreparedStatement pstmt; 
	
	public MemberDao(){
		Dbconn conn = new Dbconn();
		this.conn = conn.getConnect(); // return connection type
		
	}
		
	public int memberinsert(String memberid, String memberpwd, String membername, String membergender, long memberjumin2 , String memberaddr, String tot_memberhobby, long memberphone2, String ip, String to_date ){
		int k = 0;
		
		try{ 
			String sql = "insert into bclass_member2(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBERGENDER,MEMBERJUMIN,MEMBERADDR,MEMBERHOBBY,MEMBERPHONE,IP,WRITEDAY) values(midx_seq.nextval,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberid);
			pstmt.setString(2, memberpwd);
			pstmt.setString(3, membername);
			pstmt.setString(4, membergender);
			pstmt.setLong(5, memberjumin2);
			pstmt.setString(6, memberaddr);
			pstmt.setString(7, tot_memberhobby);
			pstmt.setLong(8, memberphone2);
			pstmt.setString(9, ip);
			pstmt.setString(10, to_date);

			k = pstmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{	
			try{
				pstmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	return k;
}
	//member info all call
	public ArrayList<MemberVo> selectMemberAll() {
		//ArrayList클래스 생성 
		ArrayList<MemberVo> alist = new ArrayList();
		
		ResultSet rs=null;
		
		String sql = "select * from bclass_member2 order by midx desc";
		
		try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();//return Resultset type
				
				while(rs.next()) {
					//rs에 담긴 data를 한줄씩 MemberVo에 담는다.
					MemberVo mv = new MemberVo();
					mv.setMidx(rs.getInt("midx"));
					mv.setMembername(rs.getString("membername"));
					mv.setWriteday(rs.getString("writeday"));
					alist.add(mv);
				}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return alist; 
	}
	
	
	//member vaild check method
	public MemberVo memberLogin(String memberid, String memberpwd) {
		int value=0;
		ResultSet rs=null;
		MemberVo mv = null;
		try {
			String sql = "select membername,memberid, memberpwd from bclass_member2 where memberid= ? and memberpwd= ?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			pstmt.setString(2, memberpwd);
			
			//pstmt.executeUpdate() 이 아닌 이유
			//- 위 sql 구문경우 단순히 값을 넣기만 하는것이 아닌 sql 구문에 해당하는 결과값이 필요한것으로 값을 받아오는 executeQuery() 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMembername(rs.getString("membername"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMemberpwd(rs.getString("memberpwd"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return mv; 
	}
	
	
	//member info one call
	public MemberVo selectOne(int midx) {
		
		ResultSet rs = null;
		MemberVo mv = null;
		
		
		try {
				String sql = "select memberid, memberjumin, memberhobby, membername, memberphone from bclass_member2 where midx= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, midx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					mv = new MemberVo();
				
					String memberid = rs.getString("memberid");
					mv.setMemberid(memberid);
					//mv.setMemberid(rs.getString("memberid"));
					Long memberjumin = rs.getLong("memberjumin");
					mv.setMemberjumin(memberjumin);
					String memberhobby = rs.getString("memberhobby");
					mv.setMemberhobby(memberhobby);
					String membername = rs.getString("membername");
					mv.setMembername(membername);
					Long memberphone = rs.getLong("memberphone");
					mv.setMemberphone(memberphone);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mv;
		
	}
	
}









