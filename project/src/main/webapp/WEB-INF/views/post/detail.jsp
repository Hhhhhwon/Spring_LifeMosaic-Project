<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>life mosaic</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/main_navigation.css" />
<link rel="stylesheet" href="../css/detailWeeklyStyle.css" />
<style>




.ck.ck-editor {
    max-width: 800px; /* 최대 가로 폭 */
    width: 100%; /* 가로 폭 */
    margin: auto; /* 가운데 정렬을 위해 margin 설정 */
}
.recommentContent i::before {
    
    color: #FOD9FF; 
}
.comment-container {
	display: inline-block;
	border-radius: 20px;
	border: 2px solid #ccc;
	padding: 10px;
}

.comment-input-wrapper {
    display: flex;
    justify-content: center; /* 가운데 정렬을 위해 */
    /* 다른 스타일 속성들 */
}

.comment-input {
    width: 100%; /* 부모 요소인 .comment-input-wrapper에 맞게 설정 */
    /* 다른 스타일 속성들 */
}



.comment-button {
	margin-left: 10px;
	padding: 5px 15px;
	border: none;
	border-radius: 10px;
	white-space: nowrap;
	cursor: pointer;
}

#bookmark:hover, #modify:hover, #btnRegisterComment:hover {
	background-color: #F0D9FF;
	transition: all 0.3s ease;
}

/* 캔버스 요소의 최대 너비 설정 */
canvas {
	max-width: 150px;
}

/* 좋아요, 싫어요 버튼 스타일링 */
#main.container {
	display: grid;
	grid-template-columns: 3fr 1fr;
	gap: 30px;
	
}

.btn-custom-like, .btn-custom-dislike {
	font-size: 1.5rem;
	padding: 10px 20px;
	transition: all 0.3s ease;
}

/* 좋아요, 싫어요 버튼에 마우스 호버 시 스타일 변경 */
.btn-custom-like:hover, .btn-custom-dislike:hover {
	transform: scale(1.1);
}

.btn-custom-like, .btn-custom-dislike {
	padding: 8px 15px; /* 여백 조절 */
	font-size: 12px; /* 글꼴 크기 조절 */
}

.btn-custom-like img, .btn-custom-dislike img {
	width: 20px; /* 이미지 너비 조절 */
	height: 20px; /* 이미지 높이 조절 */
	margin-right: 5px; /* 이미지와 텍스트 사이 여백 조절 */
}
</style>
</head>
<body>


	<header>
		<c:url var="imgPath" value="../img/logo.png" />
		<%@ include file="../fragments/navigation.jspf"%>
		<div style="margin: 110px"></div>
	</header>

	<!-- 메뉴바랑 겹치기 않기 위해 빈 공간 생성 -->
	<div id=main class="container">
		<div class="item">
        <div class="text-start me-3" style="font-size: 32px; font-weight: bold; padding: 16px 40px;">
                <c:choose>
                    <c:when test="${post.sub_category_id eq 11}">
                        <span>이거 내 잘못이야?</span>
                    </c:when>
                    <c:when test="${post.sub_category_id eq 41}">
                        <span>차사고</span><br>
                    </c:when>
                    <c:when test="${post.sub_category_id eq 42}">
                        <span>게임</span><br>
                    </c:when>
                    <c:when test="${post.sub_category_id eq 43}">
                        <span>기타(악기 아님)</span>
                    </c:when>
                    <c:when test="${post.sub_category_id eq 21}">
                        <span>나 호구냐?</span><br>
                    </c:when>
                    <c:when test="${post.sub_category_id eq 31}">
                        <span>부럽지?</span><br>
                    </c:when>
                    <c:when test="${post.sub_category_id eq 51}">
                        <span>자유</span><br>
                    </c:when>
                </c:choose>
        </div>
			<main class="my-4">
				<div class="card">
					<div class="card-body">
						<div class="mb-3">
							<div>
								<%-- 게시글 정보 --%>
								<c:url var="settingImgPage" value="/setting/settingImg" />
                                <img src="${settingImgPage}?fileName=${post.profile_url }" alt="profile" class="me-2 rounded-circle" style="width: 25px; height: 25px; border: 1px solid lightgray;"/>
								<!-- 사용자 아이콘 -->
                                
                                <c:url var="memberPostListPage" value="/member/memberPostList">
                                    <c:param name='nickname' value='${post.nickname }'/>
                                </c:url>
                                <a href="${memberPostListPage}" style="text-decoration:none; color:black;">
								    ${post.nickname}
                                </a>
								<!-- 작성자 -->
								<span class="mx-2">|</span> <i class="far fa-eye me-1"></i>
								<!-- 조회수 아이콘 -->
								${post.view_count}
								<!-- 조회수 -->
								<span class="mx-2">|</span> <span class="fw-bold " id="time">${post.created_time}</span>
								<!-- 작성 시간 -->
								<a id="bookmark" class="fs-5 fw-bold btn"
									style="border-color: #F0D9FF; border-width: 3px; padding: 3px 10px 3px 12px; float: right;">
									<i class="fas fa-bookmark me-1"
									style="margin-left: auto; margin-right: auto;"></i> <!-- 북마크 아이콘 -->
								</a>
								<div style="float: right">
									<%-- 수정 버튼 작성자와 로그인한 이용자가 같을때 보여주기--%>
									<c:if test="${signedInUser eq post.user_id}">
										<c:url var="postmodify" value="/post/modify">
											<c:param name="post_id" value="${post.post_id}"></c:param>
										</c:url>
										<a id="modify" class="btn" href="${postmodify}"
											style="border-color: #F0D9FF; border-width: 3px; margin-right: 10px; font-color: #7F7C82;">게시글
											수정</a>
									</c:if>
								</div>
							</div>
							<h2 class="fw-bold mb-3 my-3">${post.title}</h2>
							<div class="d-flex justify-content-between">
								<div class="me-4"></div>
							</div>
						</div>

						<hr class="my-4">
						<div class="my-2">
							<%-- 게시글 내용 --%>
							<input style="display: none;" class="hidden" id="post_id"
								type="number" value="${post.post_id}" readonly />
						</div>
						<div class="my-4 grow-wrap" style="height: 400px; border: none;">
						    <label for="content" class="form-label"></label>
                            
						    <div class="form-control" id="content" style="border: none;">${post.content}
                            <div id="youtube-video"></div>
                            </div>
						</div>

						<hr>
						<!-- 해시태그 보여주기 -->
						<div class="d-flex gap-2 justify-content-start">
							<c:forEach items="${tags }" var="tag">
								<span class="badge px-3 py-2 text-primary-emphasis rounded-pill"
									style="background-color: #F3F1F5 ">#${tag}</span>
							</c:forEach>
						</div>


						<div class="d-flex justify-content-center my-4">
							<div
								style="display: flex; flex-direction: column; align-items: center;">
								<div style="margin-bottom: 0px;">

									<%-- 좋아요 및 싫어요 버튼 --%>
									<button class="btn btn-custom-like" id="likeBtn" name="likeBtn" >
										<img src="https://cdn-icons-png.flaticon.com/512/1933/1933511.png " width="30" height="30" alt="" title="좋아요" class="img-small">
										좋아요
									</button>

									<button class="btn btn-custom-dislike" id="dislikeBtn" name="dislikeBtn">
										<img src="https://cdn-icons-png.flaticon.com/512/1933/1933511.png " width="30" height="30" alt="" title="싫어요" class="img-small">
										싫어요
									</button>

								</div>
								<div id="like" style="height: 10px;"></div>
								<div style="margin-top: 20px;">
									<%-- 차트 --%>
									<canvas id="donutChart"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>


				<%-- 댓글 토글 버튼에 의해서 접기/펼치기를 할 영역 --%>
				<%-- 내 댓글 목록 --%>
				<div class="my-2 card">
					<!-- 댓글 토글 버튼에 의해서 접기/펼치기를 할 영역 -->
					<div class="card-body " id="Comments">
						<div class="card card-body">
							<!-- 내 댓글 등록 -->
							<div class="comment-container">
								<div class="comment-input-wrapper">
									<c:choose>
										<c:when test="${empty signedInUser}">
											<div class="form-control" id="ctext">
												댓글을 쓰려면 <span> <a href="/project/user/signin"> <i
														class="fa fa-sign-in"></i> 로그인
												</a>
												</span> 이 필요합니다.
											</div>
										</c:when>
										<c:otherwise>
										 <textarea id="ctext" placeholder="댓글을 입력하세요" class="comment-input"></textarea>										</c:otherwise>
									</c:choose>
									<input class="d-none" id="writer" value="${signedInUser}">
									<button style="" class="comment-button" id="btnRegisterComment">등록</button>
								</div>
							</div>
							<!-- 포스트에 달려 있는 댓글 목록을 보여줄 영역 -->
							<div class="my-2" id="comments"></div>
						</div>
					</div>
				</div>
				<div class="my-2" id="comments"></div>

			</main>
			<div id=pageContainer></div>
		</div>
		<div class="item">
			<div style="position: fixed; width: 250px; top: 50%; transform: translateY(-50%);">
				<%@include file="../fragments/topwriter.jspf"%>
				<div class="p-2"></div>
				<%@include file="../fragments/detail-weekly.jspf"%>
			</div>
		</div>
	</div>

	<footer>
		<div class="item"><%@include file="../fragments/footer.jspf"%></div>
	</footer>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

	<script src="https://kit.fontawesome.com/1306328925.js"
		crossorigin="anonymous"></script>

	<script src="../js/like.js"></script>
	<script src="../js/bookmark.js"></script>
	<script>
        const signedInUser = `${signedInUser}`;
    </script>

	<script src="../js/comment.js"></script>
	<script src="../js/time-details.js"></script>
    <script src="../js/textarea.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/40.2.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/40.2.0/classic/translations/ko.js"></script>
 <script>
   document.addEventListener('DOMContentLoaded', function() {
	   
	   ClassicEditor
	    .create(document.querySelector('#ctext'))
	    .then(editor => {
	        editor.model.document.on('change:data', () => {
	            const editorValue = editor.getData();
	            document.querySelector('#ctext').value = editorValue;
	        });
	    })
	    .catch(error => {
	        console.error(error);
	    });
	   
	
	    document.getElementById('content').addEventListener('click', function(event) {
	        const clickedElement = event.target.closest('a');

	        // 클릭된 요소가 링크인지 확인
	        if (clickedElement && clickedElement.getAttribute('href')) {
	            event.preventDefault(); // 기존의 링크 이동 동작을 막음

	            // 클릭된 요소의 href 속성값 추출
	            const clickedUrl = clickedElement.getAttribute('href');
	            console.log('클릭된 링크:', clickedUrl);

	            // 클릭된 링크가 http:// 또는 https://로 시작하는지 확인
	            if (clickedUrl.startsWith('http://') || clickedUrl.startsWith('https://')) {
	                window.open(clickedUrl, '_blank'); // 새 창을 열어 링크 이동
	            } else {
	                // 프로젝트 경로와 상관없이 도메인만 추출하여 이동
	                const domain = clickedUrl.replace(/^(?:https?:\/\/)?(?:www\.)?/i, '');
	                window.open('http://' + domain, '_blank'); // 새 창을 열어 도메인 이동
	            }
	        }
	    });
	});
</script>
</body>
</html>