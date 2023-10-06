<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ include file="../layouts/header.jsp"%>

<style>
.card-text{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
.card-img-top {
	height: 200px;
	object-fit: cover;
}
</style>

<%-- 개별 페이지 --%>
<h1>100대 관광지</h1>

<%@ include file="../common/search_bar.jsp"%>

<%-- <table class="table table-striped table-hover">
	<thead>
		<tr>
			<th style="width: 60px">No</th>
			<th style="width: 100px">권역</th>
			<th>제목</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="travel" items="${list}">
			<tr>
				<td style="width: 60px">${travel.no}</td>
				<td style="width: 100px">${travel.region}</td>
				<td><a href="${cri.getLink('get')}&no=${travel.no}">
						${travel.title} </a></td>
			</tr>
		</c:forEach>
	</tbody>
</table> --%>

<div class="row">
	<c:forEach var="travel" items="${list}">
		<div class="col-sm-6 col-md-4 mb-3">
			<div class="card" style="width:100%">
				<!-- 상세보러가기 -->
				<a href="${cri.getLink('get')}&no=${travel.no}">
					<img class="card-img-top" src="${travel.image}" alt="${travel.title}">
				</a>
				<div class="card-body">
					<h4 class="card-title">
						<!-- 상세보러가기 -->
						<a href="${cri.getLink('get')}&no=${travel.no}">
							${travel.title}
						</a>
					</h4>
					<a href="#" class="heart">
						<i class="fa-regular fa-heart text-danger"></i>
					</a>
					${travel.hearts}
					<p class="card-text">${travel.summary}</p>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<sec:authorize access="hasRole('ROLE_MANAGER')">
	<div class="text-right">
		<!-- href는 상대경로 -->
		<a href="register" class="btn btn-primary"> <i class="far fa-edit"></i>
			추가
		</a>
	</div>
</sec:authorize>

<%@include file="../common/pagination.jsp"%>

<%@ include file="../layouts/footer.jsp"%>