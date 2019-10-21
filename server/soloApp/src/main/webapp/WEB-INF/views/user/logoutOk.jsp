<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>로그아웃 페이지</title>
</head>
<body>
	<h1> 로그아웃 되셨습니다. </h1>
	
	<P>  로그아웃 시간 : ${serverTime} </P>
	
	<a href="http://13.125.12.186/index.jsp"> 메인으로 </a>
</body>
</html>