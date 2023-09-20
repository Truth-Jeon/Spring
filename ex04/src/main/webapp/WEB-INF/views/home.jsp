<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello <sec:authentication property="principal.username"/>
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- isAnonymouns() == true : 로그인 안했다는 뜻. -->
<sec:authorize access="isAnonymous()">
	<a href="/security/login">로그인</a>
</sec:authorize>

<br>

<!-- isAuthenticated() == true : 로그아웃 안했다는 뜻. -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username"/>
	<form action="/security/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="로그아웃"/>
	</form>
</sec:authorize>

</body>
</html>
