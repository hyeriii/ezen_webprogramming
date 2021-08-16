<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import = "service.MemberVo" %>
<% MemberVo mv = (MemberVo)request.getAttribute("mv"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원정보 페이지

<form name="frm">
		<table border="1">
			<tr>
				<td> 회원 아이디</td>
				<td><%=mv.getMemberid() %></td>
			</tr>
			<tr>
				<td> 회원 이름</td>
				<td><%=mv.getMembername() %></td>
			</tr>
			<tr>
				<td> 회원 취미</td>
				<td><%=mv.getMemberhobby() %></td>
			</tr>
			<tr>
				<td> 회원폰</td>
				<td><%=mv.getMemberphone() %></td>
			</tr>
		</table>
</form>		
</body>
</html>