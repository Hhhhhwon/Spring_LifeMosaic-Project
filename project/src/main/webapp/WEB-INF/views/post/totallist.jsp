<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>life mosaic</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
            crossorigin="anonymous">
	</head>
	<body>
        <div class="container-fluid d-flex justify-content-center">
            <c:url var="imgPath" value="/img/logo.png" />
            <%@ include file="../fragments/navigation.jspf" %>
        </div>

        <table class="table table-striped table-hover card-body my-2">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${posts}">
                            <tr>
                                <td>${p.post_id}</td>
                                <td>
                                    <c:url var="postDetails" value="/post/detail">
                                        <c:param name="post_id" value="${p.post_id}" />
                                    </c:url>
                                    <a href="${postDetails}">${p.title}</a>
                                </td>
                                <td>${p.nickname}</td>
                                <td>${p.modified_time}</td>  
                            </tr>
                        </c:forEach>
                    </tbody>
        </table>
        <div class="pageInfo_wrap" >
            <div class="pageInfo_area">
                <ul id="pageInfo" class="pageInfo">
                
                    <!-- 이전페이지 버튼 -->
                    <c:if test="${pageMaker.prev}">
                        <li class="pageInfo_btn previous"><a href="totallist?pageNo=${pageMaker.startPage-1}">Previous</a></li>
                    </c:if>
                    
                    <!-- 각 번호 페이지 버튼 -->
                    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                        <li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? " active":"" }">
                            <a href="totallist?pageNo=${num}">${num}</a>
                        </li>
                    </c:forEach>
                    
                    <!-- 다음페이지 버튼 -->
                    <c:if test="${pageMaker.next}">
                        <li class="pageInfo_btn next"><a href="totallist?pageNo=${pageMaker.endPage + 1 }">Next</a>
                        </li>
                    </c:if> 
                    
                </ul>
            </div>
        </div>
    

    
    <form id="moveForm" method="get">   
        <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
        <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
        <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">  
        <input type="hidden" name="type" value="${pageMaker.cri.type}">    
    </form>

		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
                crossorigin="anonymous"></script>
                
        <script src="../js/page.js"></script>
        
	</body>
</html>