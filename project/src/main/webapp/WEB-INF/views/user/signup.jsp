<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Project22 회원가입</title>

	<!-- 이메일 인증 URL을 위한 user.js 설정 -->
	<script>
		var baseUrl = "<c:url value='/user/'/>";
	</script>

	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
		  rel="stylesheet"
		  integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
		  crossorigin="anonymous">

	<!-- 사용자 지정 CSS -->
	<link href="../css/signup-style.css" rel="stylesheet">
</head>
<body>
<main class="d-flex align-items-center justify-content-center" style="height: 100vh;">
	<div class="signup-container">
		<a href="/project/">
			<!-- 로고 이미지 -->
			<img src="../img/logo.png" alt="로고 이미지" />
		</a>
		<form action="${signUpPage}" method="post" onsubmit="return combineEmail()">
			<!-- 아이디 입력 필드 -->
			<div class="form-group">
				<input type="text" class="form-control" id="user_id" name="user_id" placeholder="아이디" required autofocus />
			</div>
			<div id="checkIdResult"></div>

			<!-- 닉네임 입력 필드 -->
			<div class="form-group">
				<input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임" required />
			</div>
			<div id="checkNicknameResult"></div>

			<!-- 비밀번호 입력 필드 -->
			<div class="form-group">
				<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required />
			</div>
			<span id="passwordMessage"></span>

			<!-- 이메일 입력 필드 -->
			<div class="form-group">
				<input type="text" class="form-control" name="userEmail1" id="userEmail1" placeholder="이메일" required />
				<select class="form-control" name="userEmail2" id="userEmail2">
					<option value="@naver.com">@naver.com</option>
					<option value="@daum.net">@daum.net</option>
					<option value="@gmail.com">@gmail.com</option>
				</select>
			</div>

			<!-- 본인인증 버튼 -->
			<div class="form-group">
				<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
			</div>

			<!-- 인증번호 입력 필드 -->
			<div class="form-group" style="margin-bottom: 2rem;">
				<input class="form-control" id="email_verify" name="email_verify" placeholder="인증번호 8자리를 입력해주세요!" maxlength="8" disabled>
				<!-- 인증번호 검증 결과 메시지를 표시할 요소 -->
				<div id="mail-check-warn" style="color: red;"></div>
			</div>

			<!-- 회원가입 버튼 -->
			<div class="form-group">
				<button type="submit" id="btnRegister" class="btn btn-success form-control">회원가입</button>
			</div>

			<input type="hidden" name="email" id="combinedEmail">
		</form>
	</div>
</main>

<script>
	function combineEmail() {
		var emailPart1 = document.getElementById('userEmail1').value;
		var emailPart2 = document.getElementById('userEmail2').value;
		document.getElementById('combinedEmail').value = emailPart1 + emailPart2;
		return true; // 폼을 제출합니다.
	}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/user.js"></script>
</body>
</html>