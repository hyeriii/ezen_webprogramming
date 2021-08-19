<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check(){
	var fm = document.frm;
	if(fm.subject.value==""){
		fm.subject.focus();
		alert("제목을 입력하세요");
		return false;
	}else if(fm.contents.value==""){
		fm.contents.focus();
		alert("내용을 입력하세요");
		return false;
	}else if(fm.writer.value==""){
		fm.writer.focus();
		alert("작성자를 입력하세요");
		return false;
	}
	
	fm.action = "<%=request.getContextPath()%>/board/boardWriteAction.do";
	fm.method="post";
	submit();
}
</script>
</head>
<body>
board Write page 

<form name="frm">
<table border = 1>
	<tr>
	<td>제목</td>
	<td><input type="text" name="subject"></td>
	</tr>
	
	<tr>
	<td>내용</td>
	<td><textarea name = "contents"></textarea></td>
	</tr>
	
	<tr>
	<td>작성자</td>
	<td><input type="text" name="writer"></td>
	</tr>
	
</table>
<input type="submit" onclick="check(); return false;">
<button type="reset">리셋</button>
</form>


</body>
</html>