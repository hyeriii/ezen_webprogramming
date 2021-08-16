<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(){
	/* 	var ID = document.getElementById("inputid").value;
		if(ID == ""){
			alert("아이디를 입력하세요");
			
		} */
		
		var fm= document.frm;
		
		if( fm.memberid.value == ""){
			fm.memberid.focus();
			alert("아이디를 입력하세요!!");
			return false;		
		}else if(fm.memberpwd.value==""){
			fm.memberpwd.focus();
			alert("비밀번호를 입력하세요");
			return false;
		}else if(fm.memberpwd2.value==""){
			fm.memberpwd2.focus();
			alert("비밀번호 확인을 입력하세요");
			return false ; 
		}else if(fm.memberpwd.value != fm.memberpwd2.value){
			fm.memberpwd2.focus();
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}else if(fm.membername.value == ""){
			fm.membername.focus();
			alert("이름을 입력하세요");
			return false;
		}else if(fm.memberjumin.value == "" ){
			fm.memberjumin.focus();
			alert("주민번호를 입력하세요");
			return false;
		}else if(fm.memberphone.value == "" ){
			fm.memberphone.focus();
			alert("전화번호를 입력하세요");
			return false;
		}
			//fm.action="./20_login.html"	
			//fm.action="./B_0805.jsp";
			
		//0811
		//가상경로 MemberJoinAction.do 설정 
		fm.action="<%=request.getContextPath()%>/member/memberJoinAction.do";
		fm.method = "post";
		fm.submit();
		return;
	}

	function idcheck() {
		var pattern = /[a-zA-Z]/;
		var id = document.getElementById("inputid").value;
		var count = 0;
		if (pattern.test(id)) {
			alert("사용할수있는 아이디입니다. ");
			document.frm.memberpwd.focus();
			count++;
			return count;
		} else {
			alert("아이디 형식에 맞지 않습니다");
			document.frm.memberid.focus();
			return count;
		}
	}

	function count_id() {
		var count = 0;
		count = idcheck();
		alert("count  " + count);
		if (count == 1) {
			alert("count2  " + count);
			document.getElementById("inputpwd").disabled = "false";
		} else {
			document.getElementById("inputpwd").disabled = "true";
		}
	}
</script>

<style type="text/css">
input:hover {
	background: yellow;
}

input[type="text"] {
	color: red;
	background-color: yellow;
}

input[type="password"] {
	background-color: green;
}

#ser_btn {
	background-color: white;
}
</style>
</head>
<body>
	<h3>회원가입 형식</h3>
	<!-- <form name="frm" mehtod="post" onSubmit="return check()"> -->
	<form name="frm">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="inputid" name="memberid">
					<button onclick="count_id(); return false;">idcheck</button></td>
			</tr>

			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="memberpwd" id="inputpwd"></td>
			</tr>

			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="memberpwd2"></td>
			</tr>

			<tr>
				<td>이름</td>
				<td><input type="text" name="membername"></td>
			</tr>

			<tr>
				<td>성별</td>
				<td>남<input type="radio" value="M" name="membergender" checked>
					여<input type="radio" value="F" name="membergender">
				</td>
			</tr>

			<tr>
				<td>주민번호</td>
				<td><input type="number" name="memberjumin"></td>
			</tr>

			<tr>
				<td>전화번호</td>
				<td><input type="number" name="memberphone"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><select name="memberaddr">
						<option value="전주" selected>전주</option>
						<option value="대전">대전</option>
						<option value="서울">서울</option>
						<option value="부산">부산</option>
				</select></td>
			</tr>

			<tr>
				<td>취미</td>
				<td><input type="checkbox" name="memberhobby" value="축구"
					checked>축구 <input type="checkbox" name="memberhobby"
					value="농구">농구 <input type="checkbox" name="memberhobby"
					value="야구">야구</td>
			</tr>

			<tr>
				<td colspan="2">
					<button onclick="check();return false;">가입</button> <!--  <center><input type="submit" value = "전송" name = "submit" id="ser_btn">-->
				</td>
			</tr>

		</table>

	</form>

</body>
</html>