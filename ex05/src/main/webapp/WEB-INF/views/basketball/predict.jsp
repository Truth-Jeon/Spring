<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp"%>

<script src="/resources/js/rest.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"> </script>
<script src="/resources/js/comment.js"></script>
<script src="/resources/js/reply.js"></script>

<div class="panel-body">
	<form:form modelAttribute="basketBallVO" role="form">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div class="form-group">
			<form:label path="p3">3점슛</form:label>
			<form:input path="p3" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="p2">2점슛</form:label>
			<form:input path="p2" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="trb">토탈 리바운드</form:label>
			<form:input path="trb" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="ast">도움</form:label>
			<form:input path= "ast" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="stl">스틸</form:label>
			<form:input path="stl" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<form:label path="blk">블록 퍼센티지</form:label>
			<form:input path="blk" cssClass="form-control"/>
		</div>
		
		<div class="form-group">
			<label>결과</label>
			<div>${result}</div>
		</div>
		
		<button type="submit" class="btn btn-primary list">
			<i class="fas fa-check"></i> 확인
		</button>
		
		<button type="submit" class="btn btn-primary list">
			<i class="fas fa-check"></i> 취소
		</button>
	</form:form>
</div>

<%@ include file="../layouts/footer.jsp"%>