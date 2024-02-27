<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Life mosaic</title>
		
          <style>
                .mainBorderBox{
                    display: grid;
                    grid-template-columns: 1.5fr 1.5fr 1fr;
                    grid-template-rows: 1fr 0.5fr 0.5fr 0.5fr; 
                 }
                 
                 .item:nth-child(1) {
                        grid-column-start: 1;
                        grid-column-end: 3;
                        grid-row-start: 1;
                        grid-row-end: 2;
                    }
                 
                 .gridHeader {
                    display: grid;
                    grid-template-columns: repeat(6, 1fr);
                 }
         </style>
        
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
            crossorigin="anonymous">
        <link rel="stylesheet" href="./css/main_navigation.css" />
	</head>
	 <body>
			
			<!-- 메뉴바 -->
			<c:url var="imgPath" value="./img/logo.png" />
			<%@ include file="./fragments/navigation.jspf" %>
            
            <!-- 메뉴바랑 겹치기 않기 위해 빈 공간 생성 -->
            <div style="margin:110px"></div>
             
             
             <!-- 베스트 게시글 및 TopWriter 보여주기 -->
   		 	 <main>
                    <div class="outerDiv">
                        <div class="container mainBorderBox">
                            <div class="item">
                            	<%@ include file="./fragments/main/maintopicbest.jspf" %>
                            </div>


				<div class="item">
					<%-- 오른쪽 사이드 바 : 사용 안함 --%>
					<div
						style="position: fixed; width: 250px; top: 30%; transform: translateY(-50%);">
						<%@include file="./fragments/topwriter.jspf"%>
					<a href="http://192.168.20.21:8081/ohdogcat/">
						<img src="./img/광고.png"  style="position: fixed;  margin-top:20px; width:255px; height:320px; border:1px solid #E5E1DA;">
					</a>
					</div>
				
				</div>
				
				<div class="item">
					<%@ include file="./fragments/main/fault.jspf"%>
				</div>

				<div class="item">
					<%@ include file="./fragments/main/idiot.jspf"%>
				</div>

				<div class="item"></div>

				<div class="item">
					<%@ include file="./fragments/main/envy.jspf"%>
				</div>

				<div class="item">
					<%@ include file="./fragments/main/caraccident.jspf"%>
				</div>

				<div class="item"></div>

				<div class="item">
					<%@ include file="./fragments/main/gameaccident.jspf"%>
				</div>

				<div class="item">
					<%@ include file="./fragments/main/etcaccident.jspf"%>
				</div>

				<div class="item"></div>


				<div class="item"></div>
			</div>
			<div class="item"><%@include file="./fragments/footer.jspf"%></div>
		</div>
	</main>
</body>



<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="./js/time.js"></script>
</body>
</html>