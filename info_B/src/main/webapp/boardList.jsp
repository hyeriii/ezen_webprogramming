<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="service.BoardVo" %>
<%@page import="java.util.*" %>
 <% ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
&lt; boardList page &gt;
<table border = 1>
	<tr>
		<td>��ȣ</td>
		<td>����</td>
		<td>�ۼ���¥</td>
	</tr>
	
<% for(BoardVo bv: alist){ %>
	<tr>
	<td><%=bv.getBidx() %></td>
	<td><%=bv.getSubject()%></td>
	<td><%=bv.getWriteday() %></td>
	</tr>
<% } %>
</table>

<button onclick="document.location.href='<%=request.getContextPath()%>/board/boardWrite.do'">�۾���</button>
</body>
</html>