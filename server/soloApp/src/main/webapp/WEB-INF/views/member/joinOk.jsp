<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>회원 가입 완료 페이지</title>
</head>
<body>
	<h1> 회원가입이 완료되셨습니다. </h1>
	
	ID : ${member.memId} <br />
	PW : ${member.memPw} <br />
	Mail : ${member.memMail} <br />
	
	<P>  회원 가입 완료 시간 : ${serverTime} </P>
	
	<a href="http://13.125.12.186/index.jsp"> 메인으로 </a>
</body>
</html>
