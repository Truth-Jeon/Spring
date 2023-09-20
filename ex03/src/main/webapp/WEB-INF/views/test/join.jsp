<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../layouts/header.jsp"%>
<style>
	.error{
		color: red;
		font-size: 0.9rem;
	}
</style>

<form:form modelAttribute="member" cssClass="form">
	<form:hidden path="no"/>
	
	<br>
	<div class="form-group">
		<form:label path="username"> 사용자 ID: </form:label>
		<form:input path="username" cssClass="form-control"/>
		<form:errors path="username"  cssClass="error"/>
	</div>

	<div class="form-group">
		<form:label path="password"> 비밀번호: </form:label>
		<form:password path="password" cssClass="form-control"/>
		<form:errors path="password"  cssClass="error"/>
	</div>

	<div class="form-group">
		<form:label path="password2"> 비밀번호 확인: </form:label>
		<form:password path="password2" cssClass="form-control"/>
		<form:errors path="password2"  cssClass="error"/>		
	</div>
	
	<div class="form-group">
		<form:label path="email"> 이메일: </form:label>
		<form:input path="email" cssClass="form-control"/>
		<form:errors path="email"  cssClass="error"/>
	</div>
	
	<div class="form-group" style="width:200px">
		<form:label path="birthYear">출생년도:</form:label>
		<%-- <form:select path="birthYear" items="${years}" cssClass="form-control"/> --%>
		<form:select path="birthYear" cssClass="form-control">
			<form:option value="">---</form:option>
			<form:options items="${years}"/>
		</form:select>
	</div>
	
	<div class="form-group" style="width:200px">
		<form:label path="">역할</form:label>
		<form:select path="role" cssClass="form-control">
			<form:option value="">역할선택</form:option>
			<form:options items="${roles}"/>
		</form:select>
	</div>
	
	<div class="form-group">
		<label>취미:</label>
		<form:checkboxes path="hobbies" items="${hobbies}" cssClass="ml-4 mr-2" />
	</div>
	
	<div class="form-group">
		<label>성별:</label>
		<form:radiobuttons path="gender" items="${genders}" cssClass="ml-4 mr-2" />
	</div>
	
	<div class="form-check">
		<form:label path="agreement" cssClass="form-check-label">
			<form:checkbox path="agreement" cssClass="form-check-input"/>
			동의합니다.
		</form:label>
	</div>
	<br>
	
	<div class="form-group">
		<form:label path="intro">인사말:</form:label>
		<form:textarea path="intro" cssClass="form-control" rows="5"/>
	</div>
	<br>
	
	<input type="submit"/>
</form:form>

<%@include file="../layouts/footer.jsp"%>