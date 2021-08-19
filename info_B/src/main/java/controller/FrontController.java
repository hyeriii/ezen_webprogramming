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
		
		//�Ѿ���� �����ο� ���� �ش� controller�� �����ϰ� �б⹮ �ۼ�
		
		String uri = request.getRequestURI(); //��ü�ּҰ��
		//System.out.println("���� Ǯ���(uri) " + uri);
		
		String projectname = request.getContextPath(); //������Ʈ �̸� 
		int jari = projectname.length();
		String str = uri.substring(jari);
		System.out.println("������" + str);
		//System.out.println(request.getContextPath());
		
		String[] ur = str.split("/");
		String st = ur[1];
		String st2 = ur[2];
		
		System.out.println("st : "+ st + ","+ st2);
		
		//0812 page ���� �б⹮ �ۼ� 
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
