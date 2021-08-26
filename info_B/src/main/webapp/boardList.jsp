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
<title>board List</title>
</head>
<body>
&lt; boardList page &gt;
<table border = 1>
	<tr bgcolor = #A9A9A9>
		<td>번호</td>
		<td style="width:200px;">제목</td>
		<td style="width:200px;">작성날짜</td>
	</tr>
	
<% for(BoardVo bv: alist){ %>
	<tr>
	<td><%=bv.getBidx() %></td>
	
	<td>
	<%for(int i =1; i<=bv.getNlevel() ; i++){
		out.print("&nbsp; &nbsp;");
		if(i == bv.getNlevel()){
			out.print("ㄴ");		
		}
	}
	%>
	<a href="<%=request.getContextPath()%>/board/boardContents.do?bidx=<%=bv.getBidx()%>"><%=bv.getSubject()%></a>	
	</td>

	<td><%=bv.getWriteday()%></td>
	</tr>
<% } %>
</table>

<button onclick="document.location.href='<%=request.getContextPath()%>/board/boardWrite.do'">글쓰기</button>
</body>
</html>