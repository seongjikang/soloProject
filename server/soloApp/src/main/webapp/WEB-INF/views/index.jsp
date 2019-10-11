<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SOLO 탈출 페이지</title>
</head>
<body>
	
	<h1>SOLO 탈출</h1>
	
	<c:if test="${empty member}">
		<a href="http://13.125.12.186/v1/joinForm">회원 가입</a> &nbsp;&nbsp; 
		<a href="http://13.125.12.186/v1/loginForm">로그인</a> &nbsp;&nbsp; 
	</c:if>
	
	<c:if test="${!empty member}">
		<a href="http://13.125.12.186/v1/modifyForm">회원 정보 수정</a> &nbsp;&nbsp; 
		<a href="http://13.125.12.186/v1/logout">로그아웃</a> &nbsp;&nbsp;
		<a href="http://13.125.12.186/v1/removeForm">회원탈퇴</a> &nbsp;&nbsp; 
	</c:if>
	
</body>
</html>