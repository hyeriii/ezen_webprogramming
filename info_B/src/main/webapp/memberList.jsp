<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"  %>    
<%@page import="service.MemberVo" %>
<% ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
out.print("memberlist in ");%>

<% //return type이 object라 형변환 필요 
	String memberid = (String)session.getAttribute("memberid");
	if(memberid == null ){
		response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1 style="width:500px;height:100px">
	<tr>
		<td style="height:10px">회원번호</td>
		<td>회원이름</td>
		<td>가입날짜</td>
	</tr>
<% for(MemberVo mv: alist){%>

	<tr>
		<td><%out.print(mv.getMidx());%></td>
		<td><a href="<%=request.getContextPath()%>/member/memberInfo.do?midx=<%=mv.getMidx()%>"><%=mv.getMembername()%></a></td>
		<td><%=mv.getWriteday()%></td>
	</tr>

<%	} %>
</table>
</body>
</html>