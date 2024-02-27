<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<style>


   

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
        margin-top: 50px;

    }

    #left, #center, #right {
        padding: 10px;
        margin: 10px;
    }

    #left, #right {
        flex: 1;
    }

    #center {
        flex: 3; /* #center를 세 배 크기로 설정 */
        align-items: center;
    }

    #navigation {
        font-size: 20px;
        font-weight: bold;
    }

    .menu-item {
        margin-top: 10px;
        align-items: center;
    }

    .img-box {
        width: 150px;
        height: 150px;
        border-radius: 70%;
        overflow: hidden;

    }

    .image_container {
        width: 150px;
        height: 150px;
        border-radius: 70%;
        overflow: hidden;
    }

    .profileImg {
        width: 150px;
        height: 150px;
        object-fit: cover;

    }

    #btnUpdateImg:hover {
        background-color: #6c757d; /* 원하는 색상으로 변경 */
    }

</style>
<head>
    <meta charset="UTF-8">
    <title>lifeMosaic</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
          crossorigin="anonymous">
	<link rel="stylesheet" href="../css/main_navigation.css" />
</head>
	<div class="container-fluid">
		<div id="content">
			<div id="left">
				<%@ include file="../fragments/settingNavigation.jspf"%>
				
			</div>
			
			<div class="d-flex " >
			  <div class="vr"></div>
			</div>
			<div id="center" style="padding-left:10px; padding-right:10px;">
			
				
				<div style="height: 70px; margin-left :30px; margin-right:30px; background-color: #F3F1F5;" class="d-flex align-items-center justify-content-between rounded-4  m-2 ps-3 pe-4">
       				 <div style="font-size:18px;font-weight: bold; max-width:180px; margin-left:10px;" class="px-7">프로필</div>
       				 <img alt="프로필 페이지 이미지" src="../img/userProfile.png" style="height:150px; width:150px; margin-top:40px; margin-bottom:101px; margin-right:20px;">
    
                  </div>
                 <!-- 프로필 이미지 변경 영역 -->
                 
    
					<div class="my-2 card" style="border:none; padding-top:30px; margin-left:100px; ">
					<div style="width: 155px; height: 155px; border-radius: 70%; background-color:#7F7C82; display: flex; justify-content: center; align-items: center;">
						<div class="my-2 img-box mx-auto" >
							<c:url var="settingImgPage" value="/setting/settingImg" />
							<img class="profileImg mx-auto" src="${settingImgPage}?fileName=${user.profile_url}" name="profileImg" id="profileImg" alt="프로필 사진">
						</div>
					</div>		
				<c:url var="updateImgPage" value="/setting/updateImg"></c:url>
				<form action="${updateImgPage}" method="post" enctype="multipart/form-data" >
				
				  		  <input class="form-control form-control-sm" style="margin-top:20px; width:400px;" type="file"  id="profile" name="profile" accept="image/*" onchange="setThumbnail(event);">
							<input  class="form-control" type="hidden" name="profile_url" id="profile_url"  value="${user.profile_url}">
							<input type="hidden" name="user_id" id="user_id" value="${user.user_id}">
							<div  style="margin-top:20px;">
							<button id="btnUpdateImg" name="btnUpdateImg" type="submit" class="btn btn-secondary disabled" style=" font-size:13px; font-weight:bold;border:none;float:left; background-color:#713ABE; margin-right:10px; width:90px;">변경</button>
				</form>
				<c:url var="settingBasicImg" value="/setting/settingBasicImg" />
				<form action="${settingBasicImg}"  >	
					<input type="hidden" name="user_id" id="user_id" value="${user.user_id}">
					<button type="submit" class="btn btn-secondary" style=" font-size:13px; font-weight:bold;  border:none; background-color:#610C9F;">기본 변경</button>
					
				</form>
				</div>

					</div>
			
			<!-- 닉네임 변경 영역 -->
				<div id="registerNickname" style="margin-top:60px; margin-left:60px;"  >
				
				<div style="margin-left:40px; margin-top:25px;"  >
					<label style="font-size:15px; font-weight:bold; color:#7F7C82; margin-bottom:5px;">아이디</label>
				
					<input type="text" class="form-control" id="user_name" name="user_name"
						value="${user.user_id }" style="width:400px;">
				</div>
				<div id="limitUserId" style="font-size:12px; margin-left:40px; margin-top:3px;">
					<!-- 아이디는 변경 불가라는 문장 띄우기 -->
				</div>
				
				<div style="margin-left:40px; margin-top:25px;">
					<label style="font-size:15px; font-weight:bold; color:#7F7C82; margin-bottom:5px;">이메일</label>
					<input class="form-control" id="email" type="text" name="email"
						value="${user.email }" style="width:400px;">

				</div>
				<div id="limitEmail" style="font-size:12px;margin-left:40px; margin-top:3px;">
					<!-- 이메일은 변경 불가라는 문장 띄우기 -->
				</div>
				


         

            <!-- 닉네임 변경 -->
            <div style="margin-left:40px; margin-top:25px;">
                <label style="font-size:15px; font-weight:bold; color:#7F7C82; margin-bottom:5px;">닉네임</label>
                <input type="hidden" name="user_id" id="user_id" value="${user.user_id}">
                <div class="input-group" style="width:400px;">
                    <input class="form-control" name="nickname" id="nickname" type="text" value="${user.nickname }">
                    <button type="button" class="btn" style="background-color:#BFA2DB;" name="btnCheckNickname"
                            id="btnCheckNickname">
                        <svg xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink"
                             width="16" height="16" x="0" y="0" viewBox="0 0 507.506 507.506"
                             style="enable-background:new 0 0 512 512" xml:space="preserve" class=""><g>
                            <path d="M163.865 436.934a54.228 54.228 0 0 1-38.4-15.915L9.369 304.966c-12.492-12.496-12.492-32.752 0-45.248 12.496-12.492 32.752-12.492 45.248 0l109.248 109.248L452.889 79.942c12.496-12.492 32.752-12.492 45.248 0 12.492 12.496 12.492 32.752 0 45.248L202.265 421.019a54.228 54.228 0 0 1-38.4 15.915z"
                                  fill="#ffffff" opacity="1" data-original="#000000"></path>
                        </g></svg>
                    </button>
                </div>
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button class="btn btn-secondary " name="btnUpdateNickname" id="btnUpdateNickname"
                        type="submit"
                        style=" width:100px; font-size:13px; font-weight:bold; border:none; margin-right:100px; background-color:#610C9F;">
                    저장
                </button>
            </div>
         
        </div>
    </div>


    <div id="right">
        <%@ include file="../fragments/homeNavigation.jspf" %>
    </div>


</div>
<div id="footer">
    <%@ include file="../fragments/footer.jspf" %>
</div>

</div>

<script>
    var img = null;

    function setThumbnail(event) {
        var reader = new FileReader();
        if (!img) {
            img = document.createElement("img");

        }

        reader.onload = function (event) {
            img.setAttribute("src", event.target.result);
            document.querySelector("button#btnUpdateImg").classList.remove("disabled");

        };

        reader.readAsDataURL(event.target.files[0]);
    }
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/profile-modify.js"></script>

</body>
</html>