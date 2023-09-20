<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보</h1>
	회원 ID: ${userDTO.userid}
	<br> 비밀번호: ${userDTO.password}
	<br> 비밀번호 확인: ${userDTO.password2}
	<br> email: ${userDTO.email}
	<br> 취미:
	<c:forEach var="hobby" items="${userDTO.hobbies}">
		${hobby}
	</c:forEach>
	<br> 생일:
	<fmt:formatDate value="${userDTO.birthDate}" pattern="yyyy-MM-dd" />
</body>
</html>