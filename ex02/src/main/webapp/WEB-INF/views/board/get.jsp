<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp"%>

<%-- 요즘은 거의 쓰이지 않는 기능이지만, 테스트차 해본 것.
<c:if test="${result == 'success'}">
	<script>
		alert("수정에 성공했습니다.");
	</script>
</c:if>
--%>

<script>
	$(document).ready(function() {
		/* $('.list').click(function() {
			document.forms.listForm.submit();
		});
		
		$('.modify').click(function() {
			document.forms.modifyForm.submit();
		}); */

		$('.remove').click(function() {
			//클릭 이벤트 핸들러 함수
			if (!confirm('정말 삭제할까요?'))
				return;
			//form을 얻어서 submit() 호출
			//console.log(document.forms);
			document.forms.removeForm.submit();
		});
	});
</script>

<br>
<h1 class="page-header">
	<i class="far fa-file-alt"></i> ${board.title}
</h1>

<div class="d-flex justify-content-between">
	<div>
		<i class="fas fa-user"></i> ${board.writer}
	</div>
	<div>
		<i class="fas fa-clock"></i>
		<fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" />
	</div>
</div>

<hr>

<div>${board.content}</div>

<%-- 결과 : ${result} --%>

<div class="mt-4">
	<a href="${cri.getLink('list')}" class="btn btn-primary list"> <i class="fas fa-list"></i>
		목록
	</a>
	<a href="${cri.getLinkWithBno('modify', board.bno)}" class="btn btn-primary modify"> <i class="fas fa-edit"></i>
		수정
	</a>
	<a href="#" class="btn btn-danger remove"> <i class="fas fa-trash-alt"></i>
		삭제
	</a>
</div>

<%-- <form id="listForm" action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>

<form id="modifyForm" action="/board/modify" method="get">
	<input type="hidden" id="bno" name="bno" value="${board.bno}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form> --%>

<form action="remove" method="post" name="removeForm">
	<input type="hidden" name="bno" value="${board.bno}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>

<%@ include file="../layouts/footer.jsp"%>