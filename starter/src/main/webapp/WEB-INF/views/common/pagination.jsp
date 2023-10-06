<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<ul class="pagination justify-content-center">
	<!-- 첫 페이지 -->
	<c:if test="${pageMaker.cri.pageNum > 1}">
		<li class="page-item">
			<a class="page-link" href="${pageMaker.cri.getLink(1)}">
				<i class="fa-solid fa-backward-step"></i>
			</a>
		</li>
	</c:if>

	<!-- 이전 페이지 이동 -->
	<c:if test="${pageMaker.prev}">
		<li class="page-item">
			<a class="page-link" href="${pageMaker.cri.getLink(pageMaker.startPage-1)}">
				<i class="fa-solid fa-angle-left"></i>
			</a>
		</li>
	</c:if>

	<!-- 넘버링 구간 -->
	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
		<li class="page-item ${ cri.pageNum == num ? 'active' : '' }">
			<a class="page-link" href="${cri.getLink(num)}">${num}</a>
		</li>
	</c:forEach>

	<!-- 다음 페이지 이동 -->
	<c:if test="${pageMaker.next}">
		<li class="page-item">
			<a class="page-link" href="${cri.getLink(pageMaker.endPage+1)}">
				<i class="fa-solid fa-angle-right"></i>
			</a>
		</li>
	</c:if>
	<c:if test="${pageMaker.cri.pageNum < pageMaker.totalPage}">
		<li class="page-item">
			<a class="page-link" href="${cri.getLink(pageMaker.totalPage)}">
				<i class="fa-solid fa-forward-step"></i>
			</a>
		</li>
	</c:if>
	<%-- <c:if test="${pageMaker.cri.pageNum < pageMaker.totalPage}">
		<li class="page-item"><a class="page-link"
			href="?pageNum=${num}&amount=${ pageMaker.cri.amount}"> <i
				class="fa-solid fa-forward-step"></i>
		</a></li>
	</c:if> --%>
</ul>