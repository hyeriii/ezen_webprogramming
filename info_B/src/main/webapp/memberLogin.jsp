<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
		function check(){

				var fm= document.frm;
				
				if( fm.memberid.value == ""){
					alert("아이디를 입력하세요!!");
					fm.memberid.focus();
					return false;		
				}else if(fm.memberpwd.value==""){
					alert("비밀번호를 입력하세요");
					fm.memberpwd.focus();
					return false;
				}
				
				fm.action="<%=request.getContextPath()%>/member/memberLoginAction.do";
				fm.method = "post";
				fm.submit();
				return; 
		}
</script>

</head>
<body>
<form name="frm">
<table border = 1>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="memberid"> </td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="memberpwd"></td>
	</tr>
	<tr>
		<td colspan=2 style="text-align:center"><button onclick="check(); return false;">login</button></td>
	</tr>

</table>
</form>
</body>
</html>