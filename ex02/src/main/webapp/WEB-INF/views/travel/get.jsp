<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp"%>

<script>
	$(document).ready(function() {
		$('.remove').click(function() {
			if (!confirm('정말 삭제할까요?'))
				return;
			document.forms.removeForm.submit();
		});
	});
</script>

<br>
<h1 class="page-header">
	<i class="fa-solid fa-mountain-sun"></i> ${travel.title}
</h1>

<div class="d-flex justify-content-between">
	<div>${travel.region}</div>
	<div><i class="fa-solid fa-phone"></i> ${travel.phone}</div>
</div>
<hr>

<div>${travel.description}</div>

<div class="mt-5">주소 : ${travel.address}</div>
<div id="map" style="width: 100%; height: 300px; background: gray;"></div>

<div class="mt-4">
	<a href="${cri.getLink('list')}" class="btn btn-primary list"> <i
		class="fas fa-list"></i> 목록
	</a> <a href="${cri.getLink('modify')}&no=${travel.no}"
		class="btn btn-primary modify"> <i class="fas fa-edit"></i> 수정
	</a> <a href="#" class="btn btn-danger remove"> <i
		class="fas fa-trash-alt"></i> 삭제
	</a>
</div>

<form action="remove" method="post" name="removeForm">
	<input type="hidden" name="no" value="${travel.no}" /> <input
		type="hidden" name="pageNum" value="${cri.pageNum}" /> <input
		type="hidden" name="amount" value="${cri.amount}" /> <input
		type="hidden" name="type" value="${cri.type}" /> <input type="hidden"
		name="keyword" value="${cri.keyword}" />
</form>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=47527c077dd44e34b71ffb876f21b3cc&libraries=services"></script>
<script>

let geocoder = new kakao.maps.services.Geocoder();
let address = '${travel.address}';
		
geocoder.addressSearch(address, function(result, status) {
	if (status === kakao.maps.services.Status.OK) {
	// 배열의 첫번째 위치로 이동
	var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	let mapContainer = document.getElementById('map');
	let mapOption = {
		center: coords, // 중심좌표
		level: 4 // 지도의 확대 레벨
	};

	let map = new kakao.maps.Map(mapContainer, mapOption);
	
	var marker = new kakao.maps.Marker({
		map: map,
	    position: coords
	});
	// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	// map.setCenter(coords);
	} else {
		alert('잘못된 주소입니다.');
	}
});	

</script>
<%@ include file="../layouts/footer.jsp"%>