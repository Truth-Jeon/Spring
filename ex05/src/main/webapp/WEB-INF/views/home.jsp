<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="layouts/header.jsp"%>

<style>
.carousel-inner img {
	width: 100%;
	height: 400px;
	object-fit: cover;
}
</style>

<div id="travel-carousel" class="carousel slide" data-ride="carousel">

	<!-- Indicators -->
	<ul class="carousel-indicators">
		<c:forEach items="${travels}" varStatus="status">
			<li data-target="#travel-carousel" data-slide-to="${status.index}"
				class="<c:if test="${status.first}">active</c:if>"></li>
		</c:forEach>
	</ul>

	<!-- The slideshow -->
	<div class="carousel-inner">
		<c:forEach var="travel" items="${travels}" varStatus="status">
			<div class="carousel-item <c:if test="${status.first}">active</c:if>">
				<a href="/travel/get?no=${travel.no}&amount=12">
					<img src="${travel.image}" alt="${travel.title}">
					<div class="carousel-caption">
						<h3>${travel.title}</h3>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>

	<!-- Left and right controls -->
	<a class="carousel-control-prev" href="#travel-carousel"
		data-slide="prev"> <span class="carousel-control-prev-icon"></span>
	</a> <a class="carousel-control-next" href="#travel-carousel"
		data-slide="next"> <span class="carousel-control-next-icon"></span>
	</a>
</div>

<h1>첫 화면</h1>
<%@ include file="layouts/footer.jsp"%>
