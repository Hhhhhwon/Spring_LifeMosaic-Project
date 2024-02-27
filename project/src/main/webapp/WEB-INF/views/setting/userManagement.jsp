<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>계정 관리</title>
           
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
          <link rel="stylesheet" href="../css/main_navigation.css" />
    <link href="../css/user-management-style.css" rel="stylesheet"> <!-- "내가 쓴 게시글" 스타일시트 적용 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

</head>
<body>

<div class="container-fluid">
    <div id="content">
        <div id="left">
            <%@ include file="../fragments/settingNavigation.jspf" %>
        </div>

        <div class="d-flex ">
          <div class="vr"></div>
        </div>
        <div id="center" style="padding-left:10px; padding-right:10px;">

            <div style="height: 70px; margin-left :30px; margin-right:30px; background-color: #F3F1F5;"
                 class="d-flex align-items-center justify-content-between rounded-4  m-2 ps-3 pe-4">
                <div style="font-size:18px;font-weight: bold; max-width:180px; margin-left:10px;" class="px-7">계정 관리</div>
                <img alt="프로필 페이지 이미지" src="../img/userManagement.png"
                     style="height:150px; width:150px; margin-top:40px; margin-bottom:93px; margin-right:20px;">
            </div>

            <!-- 비밀번호 영역-->
            <div style="margin-left:30px; margin-top:20px;">
            <form id="passwordChangeForm" class="form-section">
                <div class="mb-3" >
                    <label style="font-size:15px; font-weight:bold; color:#7F7C82;" for="currentPassword" class="form-label">현재 비밀번호:</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>
                <button type="button" class="btn btn-primary mb-3" id="verifyCurrentPasswordButton" style=" font-size:13px; font-weight:bold;  border:none; background-color:#610C9F;">확인</button>
                <div id="messageBox1">
                    <!-- 서버로부터의 응답 메시지가 여기에 표시됩니다 -->
                </div>
                <div class="mb-3">
                    <label style="font-size:15px; font-weight:bold; color:#7F7C82;" for="newPassword" class="form-label">새 비밀번호:</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                </div>
                <div class="mb-3">
                    <label style="font-size:15px; font-weight:bold; color:#7F7C82;" for="confirmPassword" class="form-label">비밀번호 확인:</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    <small id="passwordHelpBlock" class="form-text text-muted">
                        (비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다)
                    </small>
                </div>
                <button type="button" class="btn btn-primary" id="changePasswordButton" style=" font-size:13px; font-weight:bold;  border:none; background-color:#610C9F;">비밀번호 변경</button>
            </form>
            <div id="messageBox2">
                <!-- 서버로부터의 응답 메시지가 여기에 표시됩니다 -->
            </div>
            <form id="emailChangeForm" class="form-section">
                <div class="mb-3" style="margin-top:10px;">
                    <label style="font-size:15px; font-weight:bold; color:#7F7C82;" for="newEmail" class="form-label">새 이메일:</label>
                    <input type="email" class="form-control" id="newEmail" name="newEmail" required>
                </div>
                <button type="button" class="btn btn-primary" id="changeEmailButton" style=" font-size:13px; font-weight:bold;  border:none; background-color:#610C9F;">이메일 변경</button>
            </form>
            <div id="emailChangeMessage">
                <!-- 서버로부터의 응답 메시지가 여기에 표시됩니다 -->
            </div>
            
            </div>
        </div>
        <div id="right">
            <%@ include file="../fragments/homeNavigation.jspf" %>
        </div>
    </div>
</div>
<div id="footer">
    <%@ include file="../fragments/footer.jspf" %>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <script src="../js/passwordChange.js"></script>
    <script src="../js/changeEmail.js"></script>
</body>
</html>