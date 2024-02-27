<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>life mosaic</title>
        
        <style>
            .container {
                display: grid;
                grid-template-columns: 1fr 1fr 1fr 1fr;
                grid-template-rows: 0.8fr 1fr 1fr 1fr;
                justify-items: center;
                grid-gap:30px;
            }
            .fame {
                font-size: 600%;
                font-family: 'Diphylleia', serif;
                display: grid;
                align-content: center;
                grid-column-start: 2;
                grid-column-end: 4;
                grid-row-start: 1;
                grid-row-end: 2;
            }
            .cardContainer:nth-child(2) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 2;
                grid-column-end: 4;
                grid-row-start: 2;
                grid-row-end: 3;
            }
            .cardContainer:nth-child(3) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 2;
                grid-column-end: 3;
                grid-row-start: 3;
                grid-row-end: 4;
            }
            .cardContainer:nth-child(4) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 3;
                grid-column-end: 4;
                grid-row-start: 3;
                grid-row-end: 4;
            }
            .cardContainer:nth-child(5) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 2;
                grid-column-end: 3;
                grid-row-start: 4;
                grid-row-end: 5;
            }
            .cardContainer:nth-child(6) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 3;
                grid-column-end: 4;
                grid-row-start: 4;
                grid-row-end: 5;
            }
            .item:nth-child(7) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 1;
                grid-column-end: 2;
                grid-row-start: 1;
                grid-row-end: 5;
            }
            .item:nth-child(8) {
                transition: all 0.1s;
                padding: 30px;
                grid-column-start: 4;
                grid-column-end: 5;
                grid-row-start: 1;
                grid-row-end: 5;
            }         
            .cardContainer {
                height: 200px;
                width: 300px;
                border-width: 5px;
                background-color : white;
            }
            .topProfileUserImg {
                box-sizing: inherit;
                display: inline-block;
                width: 6rem;
                height: 6rem;
                border-radius: 50%;
                box-shadow: rgba(0, 0, 0, 0.06) 0px 0px 4px 0px;
            }  
            @font-face {
               font-family: 'Pretendard-Regular';
                src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
                font-weight: 400;
                font-style: normal;
            }
            .aaaa{
            font-family: 'Pretendard-Regular'; 
            }
     
             
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
            crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Diphylleia&display=swap" rel="stylesheet">

        
	</head>
	<body style="background-color: black;">

        <div class="container" >
            <div class="fame" style="height: 120px; color: #ffd700;" ><span>명예의 전당</span></div>
            <c:forEach var="writer" items="${writer}">
            <div class="my-3 cardContainer" style="border: 5px solid; border-radius: 20px; border-color: #ffd700">
                
                    <div class="aaaa" style="display: flex; text-align: center; justify-content: center; flex-direction: column;">
                        <c:url var="memberPostListPage" value="/member/memberPostList">
                            <c:param name='nickname' value='${writer.nickname }'/>
                        </c:url>
                            <a href="${memberPostListPage}" style="text-decoration:none; color:black;">
                <div class="card d-flex align-items-center" style="display: inline; border-width: 0px;">
                    
                    <c:url var="settingImgPage" value="/setting/settingImg" />
                    <img align="left" class="topProfileUserImg me-1" style="margin-right: 100px;" src="${settingImgPage}?fileName=${writer.profile_url }" />
                        <span class="align-middle fw-medium text-truncate d-inline-block" style="display: inline; font-size: 150%;">${writer.nickname }</span>
                </div>

                    <div align="right" >
                        <span class="align-middle me-1">
                            <svg xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink" width="25" height="25" x="0" y="0" viewBox="0 0 501.28 501.28" style="margin-bottom: 3px;enable-background:new 0 0 512 512" xml:space="preserve" class=""><g><path d="m501.28 194.37-166.02-35.04-84.62-147.06v407.5l154.9 69.24-17.98-168.72z" style="" fill="#bfa2db" data-original="#ffcd00" opacity="1" class=""></path><path d="M166.02 159.33 0 194.37l113.72 125.92-17.98 168.72 154.9-69.24V12.27z" style="" fill="#f0d9ff" data-original="#ffda44" class="" opacity="1"></path></g></svg>
                        </span><span class="text-secondary">${writer.cnt }</span>
                    </div>
                
                            </a>
                </div>
            </div>
            </c:forEach>

          <img class="item" src="../img/left.png">


          <img class="item" src="../img/right.png">

        </div>
	</body>
    
    <script src="../js/hall-of-fame.js">
    </script>

</html>