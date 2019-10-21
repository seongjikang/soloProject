<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css" href="http://13.125.12.186/css/normal.css" />
</head>
<body>
	
	<h1>Solo 로그인</h1>
	
	<form:form action="http://13.125.12.186/v1/user/login" method="post" commandName="user">
		<table>
			<tr>
				<td>ID</td>
				<td><form:input path="uuid" /></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인" ></td>
			</tr>
		</table>
	</form:form>
	
	<a href="http://13.125.12.186/index.jsp">MAIN</a>
</body>
</html>