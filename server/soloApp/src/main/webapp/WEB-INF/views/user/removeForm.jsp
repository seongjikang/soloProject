<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴 페이지</title>
<link rel="stylesheet" type="text/css" href="http://13.125.12.186/css/normal.css" />
</head>
<body>
	
	<h1>회원 탈퇴</h1>
	
	<form:form action="http://13.125.12.186/v1/remove" method="post" commandName="member">
		<input type="hidden" name="memId" value="${member.memId}">
		<table>
			<tr>
				<td>ID</td>
				<td>${member.memId}</td>
			</tr>
			<tr>
				<td>PW</td>
				<td><form:password path="memPw" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="회원 탈퇴" ></td>
			</tr>
		</table>
	</form:form>
	
	<a href="http://13.125.12.186/index.jsp">메인으로</a>
</body>
</html>