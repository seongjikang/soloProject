<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>회원 정보 수정 완료 페이지</title>
</head>
<body>
	<h1> 회원 정보가 수정 되었습니다. </h1>
	
	<%--
	<h3> Before Modify </h3>
	${memBef}
	ID : ${memBef.memId} <br />
	PW : ${memBef.memPw} <br />
	Mail : ${memBef.memMail} <br />
	--%>
	<h3> 수정된 회원 정보 </h3>
	ID : ${memAft.memId} <br />
	PW : ${memAft.memPw} <br />
	Mail : ${memAft.memMail} <br />
	
	<P>  회원 정보 수정 시간 : ${serverTime} </P>
	
	<a href="http://13.125.12.186/index.jsp"> 메인으로 </a>
</body>
</html>
