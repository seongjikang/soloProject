<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet" type="text/css" href="http://13.125.12.186/css/normal.css" />
</head>
<body>
	<h1>회원가입</h1>
	
	<form:form action="http://13.125.12.186/v1/join" method="post" commandName="member">
		<table>
			<tr>
				<td>ID</td>
				<td><form:input path="memId" /></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><form:password path="memPw" /></td>
			</tr>
			<tr>
				<td>MAIL</td>
				<td><form:input path="memMail" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="가입하기" >
					<input type="reset" value="취소하기" >
				</td>
			</tr>
		</table>
	</form:form>
	
	<a href="http://13.125.12.186/index.jsp">메인으로</a>
</body>
</html>