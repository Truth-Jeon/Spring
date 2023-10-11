<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ include file="../layouts/header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.umd.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.css"/>

<c:if test="${not empty username}">
<script>
	$(document).ready(function() {
		
		$('.remove').click(function() {
			if (!confirm('정말 삭제할까요?'))
				return;
			document.forms.removeForm.submit();
		});
		
		//jQuery Plugin 이라고 부름.
		Fancybox.bind('[data-fancybox="gallery"]', { // [] : 속성 선택자
			// Your custom options
		})
		
		/* $('.sm-images > img').click(function(e){
			let src = $(this).attr('src');
			$('.image-panel > img').attr('src', src);
		}); */
		
		$('.sm-images > img').hover(function(e) {
			let src = $(this).attr('src');
			$('.image-panel > img').attr('src', src);
		});
	});
</script>

<style>
	.thumb-images>a {
		width: 20%;
	}
	.thumb-images img {
		width: 100%;
		height: 150px;
		object-fit:cover;
		padding:2px;
	}
	.image-panel {
		width: 300px;
		height: 270px;
	}
	.image-panel > img {
		width: 300px;
		height: 200px;
	}
	.image-panel > .sm-images > img {
		width:20%;
		height: 60px;
		object-fit: cover;
		cursor: pointer;
	}
	.get-content {
		display: flex;
      	flex-wrap: wrap;
      	flex-direction: column; /*수직 정렬*/
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

<br>
<h1 class="page-header">
	<i class="fa-solid fa-mountain-sun"></i> ${travel.title}
</h1>

<!-- <div class="d-flex justify-content-between"> -->
<div class="d-flex justify-content-end">
<%-- 	<div>${travel.region}</div> --%>
	<span class="heart px-3">
		<i class="${ travel.myHeart ? 'fa-solid' : 'fa-regular' } fa-heart text-danger"
			data-tno="${travel.no}"></i>
		<span class="heart-count">${travel.hearts}</span>
	</span>
	<br>
	<div>
		<i class="fa-solid fa-phone"></i> ${travel.phone}
	</div>
</div>
<hr>

<div class="get-content">
	<!-- 강사님은 아래 div 태그에 class="clear-fix"를 적용시켜서 float를 삭제해줌. -->
	<div>
		<div class="image-panel float-left mr-3">
			<img src="${travel.image}">
			<div class="sm-images mt-1 d-flex">
				<c:forEach var="image" items="${travel.images}">
					<img src="${image}">
				</c:forEach>
			</div>
		</div>
		${travel.description}
	</div>

	<div class="thumb-images my-5 d-flex">
		<c:forEach var="image" items="${travel.images}">
			<a href="${image}" data-fancybox="gallery"><img src="${image}"></a>
		</c:forEach>
	</div>
</div>

<div class="mt-5">주소 : ${travel.address}</div>
<div id="map" style="width: 100%; height: 300px; background: gray;"></div>

<div class="mt-4">
	<a href="${cri.getLink('list')}" class="btn btn-primary list"> <i
		class="fas fa-list"></i> 목록
	</a>
	<sec:authorize access="hasAnyRole('ROLE_MANAGER')">
		<a href="${cri.getLink('modify')}&no=${travel.no}"
			class="btn btn-primary modify"> <i class="fas fa-edit"></i> 수정
		</a>
		<a href="#" class="btn btn-danger remove"> <i
			class="fas fa-trash-alt"></i> 삭제
		</a>
	</sec:authorize>
</div>

<form action="remove" method="post" name="removeForm">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="no" value="${travel.no}" />
		<input type="hidden" name="pageNum" value="${cri.pageNum}" />
		<input type="hidden" name="amount" value="${cri.amount}" />
		<input type="hidden" name="type" value="${cri.type}" />
		<input type="hidden" name="keyword" value="${cri.keyword}" />
</form>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=47527c077dd44e34b71ffb876f21b3cc&libraries=services"></script>
<script>
	let geocoder = new kakao.maps.services.Geocoder();
	let address = '${travel.address}';

	geocoder.addressSearch(address, function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			// 배열의 첫번째 위치로 이동
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			let mapContainer = document.getElementById('map');
			let mapOption = {
				center : coords, // 중심좌표
				level : 4
			// 지도의 확대 레벨
			};

			let map = new kakao.maps.Map(mapContainer, mapOption);

			var marker = new kakao.maps.Marker({
				map : map,
				position : coords
			});
			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			// map.setCenter(coords);
		} else {
			alert('잘못된 주소입니다.');
		}
	});
</script>
<%@ include file="../layouts/footer.jsp"%>