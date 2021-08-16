package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberDao;
import service.MemberVo;


@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response, String str) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//prameter ���� 
		if(str.equals("memberJoinAction.do")) {

			String memberid = request.getParameter("memberid");
			String memberpwd = request.getParameter("memberpwd");
			String membername = request.getParameter("membername");
			String membergender = request.getParameter("membergender");
			String memberjumin = request.getParameter("memberjumin");
			long memberjumin2 = Long.parseLong(memberjumin);
			String memberphone = request.getParameter("memberphone");
			long memberphone2 = Long.parseLong(memberphone);
			String memberaddr = request.getParameter("memberaddr");
			//String memberhobby = request.getParameter("memberhobby");
			String[] memberhobby = request.getParameterValues("memberhobby");
	
			String tot_memberhobby = "";
			for(int i =0 ; i< memberhobby.length;i++){
				tot_memberhobby = tot_memberhobby + memberhobby[i]; 
			}
	
			//ipclass
			//InetAddress ip = null;
			String ip = InetAddress.getLocalHost().getHostAddress();
			//out.println("ip : " + ip);
			
			//date
			Date now = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yy/MM/dd");
			String to_date = date.format(now);
			//out.println("Date : "+ to_date);
			
			MemberDao md = new MemberDao();
			int result = md.memberinsert(memberid, memberpwd, membername, membergender, memberjumin2, memberaddr, tot_memberhobby, memberphone2, ip, to_date);
			System.out.println("memberDao "+md);
			
			if(result == 1){
				//main page load
				//sendRedirect 
				//-��� ���� ��� ó�� �� �̵��ϴ°� 
				response.sendRedirect(request.getContextPath()+"/member/memberList.do");		
			}
		}else if(str.equals("memberList.do")) {
			//ȭ�鸮��Ʈ
			//ó������ 
			System.out.println("ȭ���� ����ϱ� ���� ó�������� �������մϴ�.");
			
			//db access date get  
			MemberDao md = new MemberDao();
			ArrayList<MemberVo> alist = md.selectMemberAll();
			System.out.println("alist�� ��� ����"+alist);
			
			//memberlist.jsp�� �� �Ѱ��ֱ� 
			request.setAttribute("alist", alist); // ���� ���� ? 
			System.out.println("setAttridute�̿� ");
			
			//forward��� ��� 
			RequestDispatcher rd = request.getRequestDispatcher("/memberList.jsp");
			rd.forward(request, response);
			
		}else if(str.equals("memberJoin.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/memberJoin.jsp");
			rd.forward(request, response);
		
		}else if(str.equals("memberLoginAction.do")) {
			
			String memberid = request.getParameter("memberid");
			String memberpwd = request.getParameter("memberpwd");
			
			//id,pwd �ִ� ȸ���� �����ϴ��� Ȯ�� �޼ҵ带 ����ÿ�.
			//member id, pwd vaild method create
			MemberDao md = new MemberDao();
			int result = md.memberLogin(memberid, memberpwd);
			
			if(result == 0) {
				//�ش� ȸ���� �������� ���� ���
				response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");		
				
			}else {
				//session ���� Ȱ��
				//-��� �������� ���� ���Ǻ����� ����� �� ���� 
				HttpSession session = request.getSession();
				session.setAttribute("memberid", memberid);
				
				//page load
				response.sendRedirect(request.getContextPath()+"/");						
			}
	
		}else if(str.equals("memberLogin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/memberLogin.jsp");
			rd.forward(request, response);
		}else if(str.equals("memberLogout.do")) {
			
			//session init
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect(request.getContextPath()+"/");						
		}else if(str.equals("memberInfo.do")) {
			
			String midx = request.getParameter("midx");
			int midx2 = Integer.parseInt(midx);
			
			//process
			MemberDao md = new MemberDao();
			MemberVo mv = md.selectOne(midx2);
			
			request.setAttribute("mv", mv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/memberInfo.jsp");
			rd.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
