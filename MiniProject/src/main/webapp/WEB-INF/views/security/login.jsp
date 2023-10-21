<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>

<div class="container">
	<br>
	<div class="mx-auto mt-4" style="width: 500px">
		<h1>
			<i class="fa-solid fa-right-to-bracket"></i> 로그인
		</h1>
		<c:if test="${param.error == true}">
			<div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
		</c:if>
 		<c:if test="${param.error == 'login_required'}">
			<div class="error">로그인이 필요한 서비스입니다.</div>
		</c:if>
		<fieldset>
			<form action="/security/login" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				
				<div class="form-group">
					<label for="userid">
						<i class="fa-solid fa-user"></i> 아이디
					</label>
					<input type="text" class="form-control" name="username" id="username"/>
				</div>
				
				<div class="form-group">
					<label for="password">
						<i class="fa-solid fa-lock"></i> 비밀번호
					</label>
					<input type="text" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요."/>
				</div>
				
				<!-- <div class="form-group">
					<label>
						<input type="checkbox" name="remember-me" /> 로그인 유지
					</label>
				</div> -->
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary form-control">
						<i class="fa-solid fa-right-to-bracket"></i> 로그인
					</button>
				</div>
				
				<div class="form-group">
					<button type="#" class="btn btn-primary form-control">
						<i class="fa-solid fa-user-plus"></i> 회원가입
					</button>
				</div>
			</form>
		</fieldset>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>