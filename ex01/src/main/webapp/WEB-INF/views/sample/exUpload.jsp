<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- method="post" 이므로 컨트롤러에서 @PostMapping 으로 처리해줘야 함. --%>
	<form action="/sample/exUploadPost" method="post"
		enctype="multipart/form-data">
		<%-- 꼭 동일한 이름(name)을 줘야함 --%>
		<div>
			<%-- multiple을 설정하면 파일 여러개 업로드 가능하며, 재선택 후 업로드 취소하면 초기화됨. --%>
			<input type="file" name="files" multiple="multiple"/>
		</div>
		<div>
			<input type="submit" />
		</div>
	</form>
</body>
</html>