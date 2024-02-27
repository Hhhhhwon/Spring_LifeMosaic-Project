<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Change Password</title>
	<!-- 여기에 필요한 CSS 또는 JS 파일을 포함시킬 수 있습니다 -->
</head>
<body>
<h1>비밀번호 변경</h1>
<form action="/project/user/changePassword" method="post">
	<!-- 필요한 입력 필드를 추가합니다 -->
	<input type="password" name="currentPassword" placeholder="현재 비밀번호" required>
	<input type="password" name="newPassword" placeholder="새 비밀번호" required>
	<input type="submit" value="변경">
</form>
</body>
</html>