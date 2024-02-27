<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<style>

	 body {
            margin: 10px;
            padding: 10px;
           
        }

        #top {
            padding: 20px;
            margin: 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
         #content {
            display: flex;
            justify-content: space-around;
            padding: 20px;
              margin-top:50px;
        }

        #left, #center, #right {
            padding: 10px;
            margin: 10px;
        }

        #left,#right {
            flex: 1;
        }

        #center {
            flex: 3; /* #center를 두 배 크기로 설정 */
        }
      
		.profileImg {
		  width: 25px;
		  height: 25px;
		   border-radius: 70%;
		  object-fit: cover;
		  align-items: left;
		}
	</style>
	<head>
 		<meta charset="UTF-8">
		<title>LifeMosaic</title>
		     
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" 
      	rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
      	
      	   <link rel="stylesheet" href="../css/main_navigation.css" />
	</head>
	<body>
		
		<div id="content">
			
			<div id="left">
				<%@ include file="../fragments/settingNavigation.jspf"%>
			</div>
			<div class="d-flex " >
			  <div class="vr"></div>
			</div>
			
			<div id="center" style="padding-left:10px; padding-right:10px;">
				
			   
			   <div style="height: 70px; margin-left :30px; margin-right:30px; background-color: #F3F1F5;" class="d-flex align-items-center justify-content-between rounded-4  m-2 ps-3 pe-4">
       				 <div style="font-size:18px; font-weight: bold; max-width:350px; margin-left:10px;" class="px-13">내가 쓴 댓글 (${commentLength} COMMENTS)</div>
       				 <img alt="댓글 페이지 이미지" src="../img/userMyComment.png" style="height:200px; width:200px; margin-top:40px; margin-bottom:79px; margin-right:20px;">
    
              </div>
              
              <hr style="margin-top:27px;">
              
		
			<c:choose>
                <c:when test="${not empty commentLength and commentLength != 0}">
			<c:forEach var="post" items="${comment}">
			  <div class="card " style="padding-left:40px; margin-left:50px; margin: 20px; margin-right:50px; float:center; border:none; ">
				  	<div style=" margin-left: 10px; margin-right: 20px;">
				  		<c:url var="settingImgPage" value="/setting/settingImg"/>
	                            <img class="profileImg mx-auto" src="${settingImgPage}?fileName=${post.post_user_profile_url}" name="profileImg" id="profileImg" alt="프로필 사진">
	                            <c:url var="memberPostListPage" value="/member/memberPostList">
										<c:param name='nickname'  value='${post.post_nickname}'/>
									</c:url>
									<a href="${memberPostListPage }" style="text-decoration:none; color:black;">
		                             <span class="mb-2 text-sencondary" style="font-weight:bolder; font-size: small;"  onmouseover="changeColor(this)" onmouseout="restoreColor(this)">${post.post_nickname}</span>
									</a>
	                             <span class="mb-2 text-secondary" style="text-align: right; font-size: small; margin:10px; "> ${post.post_created_time} </span>
				 	</div>
				 	<div style="margin:5px;">
				 	<a href="/project/post/detail?post_id=${post.post_id}" style="text-decoration: none; color: black;">
	                        <span style="text-align: left; margin-left: 30px; font-weight: bold;" onmouseover="changeColor(this)" onmouseout="restoreColor(this)"> 
	                        ${post.post_title}
	                        </span>
	                </a>
	                <div style="padding-left:15px; padding-top:10px;">
					 	<a href="/project/post/detail?post_id=${post.post_id}" style="text-decoration: none; color: black;">
		                        <span style="text-align: left; margin-left: 30px; color:#711DB0; font-weight:bolder; font-size:13px;" onmouseover="changeColor(this)" onmouseout="restoreColor_comment(this)"> 
	                	<svg xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink" width="14" height="14" x="0" y="0" viewBox="0 0 512 512" style="enable-background:new 0 0 512 512; margin-bottom:2px;" xml:space="preserve" class=""><g><path d="M222.766 80.234a47 47 0 0 1 66.468-66.468l209 209a47 47 0 0 1 0 66.468l-209 209a47 47 0 0 1-66.468-66.468L398.532 256zm-209 418a47 47 0 0 0 66.468 0l209-209a47 47 0 0 0 0-66.468l-209-209a47 47 0 0 0-66.468 66.468L189.532 256 13.766 431.766a47 47 0 0 0 0 66.468z" fill="#7f7c82" opacity="1" data-original="#000000" class=""></path></g></svg>
		                        ${post.comment_content}
		                        </span>
		                 </a>
				 	</div>
				 	<div style="margin-left: 10px; margin-top: 10px;">
							<c:url var="categoryListPage" value="/post/list/" >
							                <c:param name="sub_category_id" value="${post.post_subcategory_id}"></c:param>
							            </c:url>
		                            	<a href="${categoryListPage}" style="text-decoration:none;">
				                            <span class="badge mb-2" style=" margin-left:13px; color : #AB49A0; background-color: #F3F1F5;">${post.post_subcategory }</span>
		                            	</a>
		                            		<c:choose>
		                            		<c:when test="${not empty post.hashTag }">
				                            	<c:forEach var="hashTag" items="${post.hashTag }">
				                            		<span style="margin-left:3px; font-size:13px; color:#49108B;"> #${hashTag }</span>
				                            	</c:forEach>	
		                            		
		                            		</c:when>
		                            	
		                            	</c:choose>
                                <div style="margin-right: 10px; float:inline-end;">
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="16" height="16"><g id="_01_align_center" data-name="01 align center">
                                    <path d="M23.821,11.181v0C22.943,9.261,19.5,3,12,3S1.057,9.261.179,11.181a1.969,1.969,0,0,0,0,1.64C1.057,14.739,4.5,21,12,21s10.943-6.261,11.821-8.181A1.968,1.968,0,0,0,23.821,11.181ZM12,19c-6.307,0-9.25-5.366-10-6.989C2.75,10.366,5.693,5,12,5c6.292,0,9.236,5.343,10,7C21.236,13.657,18.292,19,12,19Z"/><path d="M12,7a5,5,0,1,0,5,5A5.006,5.006,0,0,0,12,7Zm0,8a3,3,0,1,1,3-3A3,3,0,0,1,12,15Z"/></g></svg>
                                    <span style="font-size: small; color:#B2B2B2;">${post.post_view_count } views</span>
                                   
                                    
                                    <svg xmlns="http://www.w3.org/2000/svg" id="Outline" viewBox="0 0 24 24" width="16" height="16"><path d="M22.773,7.721A4.994,4.994,0,0,0,19,6H15.011l.336-2.041A3.037,3.037,0,0,0,9.626,2.122L7.712,6H5a5.006,5.006,0,0,0-5,5v5a5.006,5.006,0,0,0,5,5H18.3a5.024,5.024,0,0,0,4.951-4.3l.705-5A5,5,0,0,0,22.773,7.721ZM2,16V11A3,3,0,0,1,5,8H7V19H5A3,3,0,0,1,2,16Zm19.971-4.581-.706,5A3.012,3.012,0,0,1,18.3,19H9V7.734a1,1,0,0,0,.23-.292l2.189-4.435A1.07,1.07,0,0,1,13.141,2.8a1.024,1.024,0,0,1,.233.84l-.528,3.2A1,1,0,0,0,13.833,8H19a3,3,0,0,1,2.971,3.419Z"/></svg>
                                      <span style="font-size: small; color:#B2B2B2;"> ${post.post_like_count } likes</span>
                                      
                                      <svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="16" height="16"><path d="M12.009,23.665c-.476,0-.955-.168-1.337-.507l-3.748-3.157h-2.923c-2.206,0-4-1.794-4-4V4C0,1.794,1.794,0,4,0H20c2.206,0,4,1.794,4,4v12c0,2.206-1.794,4-4,4h-2.852l-3.847,3.18c-.362,.322-.825,.484-1.293,.484ZM4,2c-1.103,0-2,.897-2,2v12c0,1.103,.897,2,2,2h3.289c.236,0,.464,.083,.645,.235l4.047,3.41,4.17-3.416c.18-.148,.405-.229,.638-.229h3.212c1.103,0,2-.897,2-2V4c0-1.103-.897-2-2-2H4Z"/></svg>
									
									<span style="font-size: small; color:#B2B2B2;">${post.comment_count } comments</span>
                                </div>
                        </div>
			 	</div>
			</div>
				<hr style="margin-left:5px;">
			</c:forEach>
						
						<div class="d-flex justify-content-center" style="pading:10px; margin:20px;">
						<nav class="pagination pagination-sm" style="background-color: #F3F1F5;">
						    <c:forEach var="pageNumber" begin="1" end="${pagesCount}">
						     <c:url var="settingMyCommentPage" value="/setting/userMyComment"/>
						      <li class="page-item ${pageNumber == currentPage ? 'active' : ''}" style="display: inline;">
						        <a class="page-link" href="${settingMyCommentPage}?currentPage=${pageNumber}"
						        style="color: ${pageNumber == currentPage ? 'white' : '#7F7C82'}; /* active일 때 글자색을 원하는 색상으로 설정 */ background-color: ${pageNumber == currentPage ? '#BFA2DB' : '#F3F1F5'}; /* active일 때 배경색을 원하는 색상으로 설정 */ border-color: #F3F1F5;">
						          ${pageNumber}
						        </a>
						      </li>
						    </c:forEach>
						</nav>
						</div>
					
			</c:when>
			<c:otherwise>
				
				<div class="d-flex justify-content-center" style="margin-top:30px; margin-bottom:200px;">
		         <video  width="50" height="50" preload="none"  style="padding-bottom:10px; background: transparent  url('https://cdn-icons-png.flaticon.com/512/7158/7158908.png') 50% 50% / fit no-repeat;"  autoplay="autoplay" loop="true" muted="muted" playsinline="">
		              <source src="https://cdn-icons-mp4.flaticon.com/512/7158/7158908.mp4" type="video/mp4">
		          </video>
		          <label style="font-size:25px; font-weight:bold;">댓글이 존재하지 않습니다.</label>
		         	</div>
			</c:otherwise>
			</c:choose>			
			
			
			
			</div>
			<div id="right">
			 <%@ include file="../fragments/homeNavigation.jspf"%>
			</div>
				
		
		</div><!-- end div="content" -->
		 	<div id="footer" >
				 <%@ include file="../fragments/footer.jspf"%>
			</div>
    
    <script>
		 function changeColor(element) {
		        element.style.color = '#6528F7'; // 마우스를 올렸을 때 글씨 색상 변경
		    }

		    function restoreColor(element) {
		        element.style.color = 'black'; // 마우스를 벗어났을 때 원래의 글씨 색상으로 복원
		    }
		    
		    function restoreColor_comment(element) {
		        element.style.color = '#711DB0'; // 마우스를 벗어났을 때 원래의 글씨 색상으로 복원
		    }
		</script>
    
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" 
       integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
       
		<!-- 사용할 js 폴더 넣기<script src="../js/profile-modify.js"></script>-->
	</body>
</html>