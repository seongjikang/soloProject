<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>회원 탈퇴 완료 페이지</title>
</head>
<body>
	<h1> 이용해주셔서 감사합니다. </h1>
	
	ID : ${member.memId}<br />
	
	<P>  회원 탈퇴 시간 : ${serverTime} </P>
	
	<a href="http://13.125.12.186/index.jsp"> 메인으로 </a>
</body>
</html>