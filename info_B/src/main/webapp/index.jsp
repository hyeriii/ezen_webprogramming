<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원아이디는 <%=session.getAttribute("memberid") %> 입니다. 
<a href="<%=request.getContextPath()%>/member/memberJoin.do">회원가입하기</a>
<a href="<%=request.getContextPath()%>/member/memberLogin.do">로그인하기</a>
<a href="<%=request.getContextPath()%>/member/memberList.do">리스트가기</a>
<a href="<%=request.getContextPath()%>/board/boardList.do">게시글 리스트가기</a>
<% if(session.getAttribute("memberid") != null){%>
<button onclick="document.location.href='<%=request.getContextPath()%>/member/memberLogout.do'">로그아웃</button>
<% } %>
</body>
</html>