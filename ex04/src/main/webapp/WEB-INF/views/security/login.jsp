<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Login Page</h1>

<c:if test="${param.error == 'true'}">
	<div class="error">
		사용자 ID 또는 비밀번호가 일치하지 않습니다.
	</div>
</c:if>

<form action="/security/login" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	사용자 ID :
	<input type="text" name="username"/><br>
	
	비밀번호 :
	<input type="password" name="password"/><br>
	
	<input type="submit" value="로그인"/>
</form>