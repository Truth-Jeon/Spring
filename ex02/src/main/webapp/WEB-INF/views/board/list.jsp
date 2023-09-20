<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layouts/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- <script src="/resources/js/search.js"></script> -->

<%-- 개별 페이지 --%>
<br>
<h1>
	<i class="fas fa-list"></i> 게시글 목록
</h1>

<%@ include file="../common/search_bar.jsp" %>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th style="width: 60px">No</th>
			<th>제목</th>
			<th style="width: 100px">작성자</th>
			<th style="width: 130px">등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${list}">
			<tr>
				<td style="width: 60px">${board.bno}</td>
				<!-- href는 상대경로 -->
				<td>
					<%-- <a href="get?bno=${board.bno}">${board.title}</a> --%>
					<a class="move" href="${cri.getLinkWithBno('get', board.bno)}">${board.title}</a>
				</td>
				<td style="width: 100px">${board.writer}</td>
				<td style="width: 130px">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="text-right">
	<!-- href는 상대경로 -->
	<a href="register" class="btn btn-primary"> <i class="far fa-edit"></i>
		글쓰기
	</a>
</div>

<%@include file="../common/pagination.jsp" %>

<%-- <form id="actionForm" action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
	<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
	<input type="hidden" name="type" value="${pageMaker.cri.type}" /> <input
		type="hidden" name="keyword" value="${pageMaker.cri.keyword}" />
</form> --%>

<%@ include file="../layouts/footer.jsp"%>