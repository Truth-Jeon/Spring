<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layouts/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet"
	href="/resources/css/summernote/summernote-lite.min.css">
<script src="/resources/js/summernote/summernote-lite.min.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.min.js"></script>

<script>
	<!-- jquery의 기능을 확장시키는 플러그인 : jQuery Plugin -->
	$(document).ready(function() {
		$('#content').summernote({
			height : 300, //에디터 높이
			focus : true, //에디터 로딩 후 포커스를 맞출지 여부
			lang : "ko-KR", //한글 설정
		});
		
		const attaches = $('[name="files"]');
		const attachList = $('#attach-list');
		
		attaches.change(function (e) {
			let fileList = '';
			for(let file of this.files) { //this = <input type="file">
			//파일 업로드 시 나오는 파일단위 소숫점 변경하고 싶으면 formatBytes() 안에 몇째까지 표시되게 할건지 숫자 넣으면 됨.	
			let fileStr = `
				<div>
					<i class="fa-solid fa-file"></i>
					\${file.name}(\${file.size.formatBytes(2)})
				</div>
				`;
				fileList += fileStr;
			}
			attachList.html(fileList);
		})
	});
	// 기본 글꼴 설정
	$('#summernote').summernote('fontName', 'Arial');
</script>
<br>
<h1 class="page-header">
	<i class="far fa-edit"></i> Board Register
</h1>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form modelAttribute="board" role="form"
			action="?_csrf=${_csrf.token}"
			enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<!-- method가 default로 post -->
			<form:hidden path="bno"/>
			<form:hidden path="writer" value="${username}"/>
			
			<div class="form-group">
				<form:label path="title">제목</form:label>
				<form:input path="title" cssClass="form-control"/>
				<form:errors path="title" cssClass="error"/>
			</div>
			<div class="form-group">
				<label for="attaches">첨부파일</label>
				<div id="attach-list" class="my-1"></div>
				<input type="file" class="form-control" multiple name="files"/>
			</div>
			<div class="form-group">
				<form:label path="content">내용</form:label>
				<form:textarea path="content" cssClass="form-control"></form:textarea>
				<form:errors path="content" cssClass="error"/>
			</div>
			<button type="submit" class="btn btn-primary">
				<i class="fas fa-check"></i> 확인
			</button>
			<button type="reset" class="btn btn-primary">
				<i class="fas fa-undo"></i> 취소
			</button>
			<a href="javascript:history.back()" class="btn btn-primary"> <i class="fas fa-list"></i>
				목록
			</a>
		</form:form>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>