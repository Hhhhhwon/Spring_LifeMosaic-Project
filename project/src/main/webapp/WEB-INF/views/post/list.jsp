<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
    #list.container {
        display: grid;
        grid-template-columns: 3fr 1fr;
        gap: 30px;
    }
    
	.post-title a:hover .hover-text {
	    color: #BFA2DB; 
	    text-decoration: none;
        transition-duration: 0.3s;
	}
    
    #title.title:hover {
        color: #938F94; 
        text-decoration: none;
        transition-duration: 0.3s;
    }

.post-title a:hover .hover-text {
	color: #BFA2DB;
	text-decoration: none;
	transition-duration: 0.3s;
}

    #create:hover {
        background-color: #F0D9FF;
        transition-duration: 0.3s;
	
    }

body {
	background-color: #fff;
}

.post-items {
	list-style-type: none;
	padding: 0;
}

.post-item {
	display: flex;
	align-items: center;
	padding: 20px;
	border-radius: 8px;
	margin-bottom: 10px;
	background-color: #F3F1F5;
	transition: background-color 0.3s ease;
}

.post-details {
	flex: 1;
}

.post-info {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.post-nickname {
	font-weight: bold;
	color: #333;
}


.post-title {
	margin: 8px 0;
	font-size: 1.1em;
	font-weight: bold; /* 글 목록 리스트를 굵게 변경 */
}

.post-link {
	color: #007bff;
	text-decoration: none;
	transition: color 0.3s ease;
}

    .post-created-time {
        font-size: 1em; /* 시간 폰트를 좀 더 크게 변경 */
        font-weight: bold; /* 시간 폰트를 굵게 변경 */
        color: #777;
    }
    
      /* 페이지 버튼의 숫자 색상을 진한 보라색으로 변경합니다. */
  .pagination .page-item .page-link span {
    color: #6a0d98; /* 진한 보라색으로 숫자 색상을 지정합니다. */
  }

  /* 활성화된 페이지 버튼의 배경색을 연한 보라색으로 변경합니다. */
  .pagination .page-item .page-link.active {
    background-color: #e0c3ff; /* 연한 보라색 배경색을 지정합니다. */
    border-color: #e0c3ff; /* 선택된 페이지 버튼의 테두리 색상을 지정합니다. */
  }

</style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
            crossorigin="anonymous">
        <link rel="stylesheet" href="../../css/main_navigation.css" />
        <link rel="stylesheet" href="../../css/detailWeeklyStyle.css" />

	</head>
	<body>
	
		    <!-- 내비게이션 include 시 경로 및 겹치는 div 만들어줘야 함 -->
        <div class="container-fluid d-flex justify-content-center">
            <c:url var="imgPath" value="/img/logo.png" />
            <%@ include file="../fragments/navigation.jspf" %>
        </div>
        <div style="margin:100px"></div>
        
        
<div id="list" class="container">
<div class="item">
        <div class="d-flex align-items-center justify-content-between rounded-4 p-3 my-3" style="height: 70px;">
            <div class="text-start me-3" style="font-size: 32px; font-weight: bold; padding: 16px 40px;">
                <c:choose>
                    <c:when test="${cId eq 11}">
                        <span>이거 내 잘못이야?</span>
                    </c:when>
                    <c:when test="${cId eq 41}">
                        <span>차사고</span><br>
                    </c:when>
                    <c:when test="${cId eq 42}">
                        <span>게임</span><br>
                    </c:when>
                    <c:when test="${cId eq 43}">
                        <span>기타(악기 아님)</span>
                    </c:when>
                    <c:when test="${cId eq 21}">
                        <span>나 호구냐?</span><br>
                    </c:when>
                    <c:when test="${cId eq 31}">
                        <span>부럽지?</span><br>
                    </c:when>
                    <c:when test="${cId eq 51}">
                        <span>자유</span><br>
                    </c:when>
                </c:choose>
        </div>
                <!-- 위에 여백을 주기 위한 마진 추가 -->
                <div style="float:end">
						<c:url var="createPage" value="/post/create">
							<c:param name="sub_category_id" value="${cId}"></c:param>
						</c:url>
						<a id="create" class="btn fs-6 fw-bold" href="${createPage}"
							style="border-color: #711DB0; border-width: 2px; margin-right: 10px;">
							<i class="fas fa-pencil-alt me-2"></i>글 쓰기
						</a>
						</div>
    </div>
    <hr style= "background: #7F7C82; height:3px; border:0;">

        
        
    <div class="item"></div>
   
        
        
 
        
        
        
        
        
        


 <div class="row justify-content-center" >
    <div>
        <div class="card-body my-2">
            <div class="post-list">
                <ul class="post-items">
                    <c:forEach var="p" items="${posts}">
                        <%-- 게시글 하나 --%>
                        <div class="border-bottom py-2">
                            <%-- 첫 번째 줄 --%>
                            <div class="text-body-secondary d-flex mb-1">
                                <input id="post_id" class="d-none" value="${p.post_id }"/>
                                <div class="d-flex flex-fill align-items-center">
                                    <%-- 프로필 이미지 --%>
                                    <c:url var="settingImgPage" value="/setting/settingImg" />
                                    <img src="${settingImgPage}?fileName=${p.profile_url }" alt="profile" class="me-2 rounded-circle" style="width: 25px; height: 25px; border: 1px solid lightgray;"/>
                                    
                                    <div class="fw-semibold" style="font-size:12px;">
                                        <%-- 닉네임 --%>
                                        
                                        <c:url var="memberPostListPage" value="/member/memberPostList">
                                            <c:param name='nickname' value='${p.nickname }'/>
                                        </c:url>
                                        <a href="${memberPostListPage}" style="text-decoration:none; color:black;">
                                        <span style="font-size: 130%;">${p.nickname }</span>
                                        </a>
                                        <%-- 작성 시간 --%>
                                        <span class="mx-1"> · </span>
                                        <span class="time">${p.created_time }</span>
                                    </div>
                                    
                                </div>
                                
                                <div class="text-secondary" style="font-size: 12px;">
        
                                    <%-- 조회수 --%>
                                    <span class="me-2"> 
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="13" height="13">
                                                <g id="_01_align_center" data-name="01 align center">
                                                    <path d="M23.821,11.181v0C22.943,9.261,19.5,3,12,3S1.057,9.261.179,11.181a1.969,1.969,0,0,0,0,1.64C1.057,14.739,4.5,21,12,21s10.943-6.261,11.821-8.181A1.968,1.968,0,0,0,23.821,11.181ZM12,19c-6.307,0-9.25-5.366-10-6.989C2.75,10.366,5.693,5,12,5c6.292,0,9.236,5.343,10,7C21.236,13.657,18.292,19,12,19Z" />
                                                    <path d="M12,7a5,5,0,1,0,5,5A5.006,5.006,0,0,0,12,7Zm0,8a3,3,0,1,1,3-3A3,3,0,0,1,12,15Z" />
                                                </g>
                                            </svg>
                                        ${p.view_count } views
                                    </span>
            
                                    <!-- 좋아요수 -->
                                    <span class="me-2"> <svg xmlns="http://www.w3.org/2000/svg"
                                            id="Outline" viewBox="0 0 24 24" width="13" height="13">
                                                <path
                                                d="M22.773,7.721A4.994,4.994,0,0,0,19,6H15.011l.336-2.041A3.037,3.037,0,0,0,9.626,2.122L7.712,6H5a5.006,5.006,0,0,0-5,5v5a5.006,5.006,0,0,0,5,5H18.3a5.024,5.024,0,0,0,4.951-4.3l.705-5A5,5,0,0,0,22.773,7.721ZM2,16V11A3,3,0,0,1,5,8H7V19H5A3,3,0,0,1,2,16Zm19.971-4.581-.706,5A3.012,3.012,0,0,1,18.3,19H9V7.734a1,1,0,0,0,.23-.292l2.189-4.435A1.07,1.07,0,0,1,13.141,2.8a1.024,1.024,0,0,1,.233.84l-.528,3.2A1,1,0,0,0,13.833,8H19a3,3,0,0,1,2.971,3.419Z" /></svg>
                                        ${p.like_point } likes
                                    </span>
            
            
                                    <!-- 댓글수 -->
                                    <span class="me-2"> <svg xmlns="http://www.w3.org/2000/svg"
                                            id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="13"
                                            height="13">
                                                <path
                                                d="M12.009,23.665c-.476,0-.955-.168-1.337-.507l-3.748-3.157h-2.923c-2.206,0-4-1.794-4-4V4C0,1.794,1.794,0,4,0H20c2.206,0,4,1.794,4,4v12c0,2.206-1.794,4-4,4h-2.852l-3.847,3.18c-.362,.322-.825,.484-1.293,.484ZM4,2c-1.103,0-2,.897-2,2v12c0,1.103,.897,2,2,2h3.289c.236,0,.464,.083,.645,.235l4.047,3.41,4.17-3.416c.18-.148,.405-.229,.638-.229h3.212c1.103,0,2-.897,2-2V4c0-1.103-.897-2-2-2H4Z" /></svg>
                                        ${p.comment_count} comments
                                    </span>
                                </div>
                            </div>
            
            
                            <!-- 두 번째 줄/ 게시글 제목 -->
                            <div class="fw-bold py-1" style="font-size: 110%; margin: 15px;">
                                <c:url var="postDetails" value="/post/detail">
                                    <c:param name="post_id" value="${p.post_id}" />
                                </c:url>
                                <a id=title class="nav-link text-truncate title" style="" href="${postDetails }">${p.title }</a>
                            </div>
                            <div class="tagDiv" data-id="${p.post_id}"></div>
                        </div>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

	<div class="item"></div>



			



				<nav aria-label="Page navigation example" >
					<ul class="pagination justify-content-center" >
						<!-- 이전 페이지 버튼 -->
						<c:if test="${pageMaker.prev}">
							<li class="page-item"><a class="page-link"
								href="?sub_category_id=${cId}&pageNo=${pageMaker.startPage-1}">Previous</a>
							</li>
						</c:if>

						<!-- 각 번호 페이지 버튼 -->
						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="page-item ${pageMaker.cri.pageNum == num ? 'active' : ''}" >
								<c:choose>
									<c:when test="${pageMaker.cri.pageNum == num}">
										<span class="page-link disabled" >${num}</span>
									</c:when>
									<c:otherwise>
										<a class="page-link"
											href="?sub_category_id=${cId}&pageNo=${num}" style="color:#711DB0;">${num}</a>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>

						<!-- 다음 페이지 버튼 -->
						<c:if test="${pageMaker.next}">
							<li class="page-item"><a class="page-link"
								href="?sub_category_id=${cId}&pageNo=${pageMaker.endPage + 1}">Next</a>
							</li>
						</c:if>
					</ul>
				</nav>
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

	<!-- 페이지 하단에 loginModal.jsp 포함 -->
	<%@ include file="../user/loginModal.jsp" %>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/1306328925.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="../../js/time.js"></script>
    <script src="../../js/hashtag.js"></script>

</body>
</html>