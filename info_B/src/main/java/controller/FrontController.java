package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//넘어오는 가상경로에 따라서 해당 controller를 실행하게 분기문 작성
		
		String uri = request.getRequestURI(); //전체주소경로
		//System.out.println("가상 풀경로(uri) " + uri);
		
		String projectname = request.getContextPath(); //프로젝트 이름 
		int jari = projectname.length();
		String str = uri.substring(jari);
		System.out.println("가상경로" + str);
		//System.out.println(request.getContextPath());
		
		String[] ur = str.split("/");
		String st = ur[1];
		String st2 = ur[2];
		
		System.out.println("st : "+ st + ","+ st2);
		
		//0812 page 별로 분기문 작성 
		if(st.equals("member")) {
			//System.out.println("member controller object create ");
			MemberController mc = new MemberController();
			mc.doGet(request, response,st2);
		}else if(st.equals("board")) {
			System.out.println("call doget");
			BoardController bc = new BoardController();
			bc.doGet(request, response,st2);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
