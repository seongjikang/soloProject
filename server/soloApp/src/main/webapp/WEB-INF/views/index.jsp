<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>MAIN</h1>
	
	<c:if test="${empty member}">
		<a href="http://13.125.12.186/v1/joinForm">JOIN</a> &nbsp;&nbsp; 
		<a href="http://13.125.12.186/v1/loginForm">LOGIN</a> &nbsp;&nbsp; 
	</c:if>
	
	<c:if test="${!empty member}">
		<a href="http://13.125.12.186/v1/modifyForm">MODIFY</a> &nbsp;&nbsp; 
		<a href="http://13.125.12.186/v1/logout">LOGOUT</a> &nbsp;&nbsp;
		<a href="http://13.125.12.186/v1/removeForm">REMOVE</a> &nbsp;&nbsp; 
	</c:if>
	
</body>
</html>