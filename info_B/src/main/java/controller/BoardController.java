package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardDao;
import service.BoardVo;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response, String str) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(str.equals("boardList.do")) {
			
			HttpSession session= request.getSession();
			String memberid = (String)session.getAttribute("memberid");
			
			String link = request.getContextPath()+"/board/boardList.do";
			session.setAttribute("link", link);
			
			if(memberid == null) {
				response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");	
			}else {
				BoardDao bd = new BoardDao();
				ArrayList<BoardVo> alist = bd.selectAll();
				request.setAttribute("alist", alist);
				
				RequestDispatcher rd = request.getRequestDispatcher("/boardList.jsp");
				rd.forward(request, response);

			}
		}else if(str.equals("boardWrite.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/boardWrite.jsp");
			rd.forward(request, response);
		}else if(str.equals("boardWriteAction.do")) {
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			BoardDao bd = new BoardDao();
			int result =bd.boardWrite(subject, contents, writer, ip);
			if(result == 1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
