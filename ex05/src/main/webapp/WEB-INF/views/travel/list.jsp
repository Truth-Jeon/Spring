<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ include file="../layouts/header.jsp"%>

<c:if test="${not empty username}">
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
.fa-heart {
	cursor: pointer;
}
</style>
<script src="/resources/js/rest.js"></script>
<script>
	$(document).ready(function() {
		let username = '${username}';
		
		const BASE_URL = '/api/travel/heart';
		
		//좋아요 추가
		$('span.heart').on('click', '.fa-heart.fa-regular', async function(e){
			let tno = parseInt($(this).data("tno"));
			let heart = {tno, username};//HeartVO랑 맵핑
			console.log(heart);
			
			//.closest() -> () 안에 들어가는 조건을 만족하는 근처의 부모태그
			//.find() 자식 클래스 찾는 메소드..
			await rest_create(BASE_URL + "/add", heart);
			let heartCount = $(this).parent().find(".heart-count");
			console.log(heartCount);
			let count = parseInt(heartCount.text());
			heartCount.text(count+1);
			
			//아이콘교체
			$(this)
				.removeClass('fa-regular')
				.addClass('fa-solid');
		});
		
		//좋아요 제거
		$('span.heart').on('click', '.fa-heart.fa-solid', async function(e){
			let tno = parseInt($(this).data("tno"));
			await rest_delete(`\${BASE_URL}/delete?tno=\${tno}&username=\${username}`);
			let heartCount = $(this).parent().find(".heart-count");
			console.log(heartCount);
			let count = parseInt(heartCount.text());
			heartCount.text(count-1);
			
			$(this)
				.removeClass('fa-solid')
				.addClass('fa-regular');
		});
	});
</script>
</c:if>

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
					<span class="heart">
						<i class="${ travel.myHeart ? 'fa-solid' : 'fa-regular' } fa-heart text-danger"
						data-tno="${travel.no}"></i>
						<span class="heart-count">${travel.hearts}</span>
					</span>
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