<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces= "true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>이메일 인증</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
	<body>
		<div class="form-group email-form">
			<label for="email">이메일</label>
			<div class="input-group">
				<input type="text" class="form-control" name="userEmail1" id="userEmail1" placeholder="이메일" >
				<select class ="form-control" name="userEmail2" id="userEmail2" >
					<option>@naver.com</option>
					<option>@daum.net</option>
					<option>@gmail.com</option>
				</select>
			</div>
			<div class ="input-group-addon">
				<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
			</div>
			<div class="mail-check-box">
				<input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
			</div>
			<span id="mail-check-warn"></span>
		</div>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
				crossorigin="anonymous"></script>
	</body>
</html>