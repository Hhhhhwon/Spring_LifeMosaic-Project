<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!-- 로그인 모달창 CSS -->
<link rel="stylesheet" href="../../css/login-modal.css" />

<!-- 로그인 모달창 -->
<div id="loginModal" class="modal">
	<div class="modal-content">
		<span class="close-button">&times;</span>
		<h2>로그인이 필요합니다</h2>
		<p>로그인이 필요한 작업이므로 로그인 페이지로 이동합니다.</p>
		<input type="hidden" name="preLoginUrl" id="preLoginUrl">
		<button id="loginRedirectButton">로그인 페이지로 이동</button>
	</div>
</div>

<!-- 로그인 모달창 JavaScript -->
<script src="../../js/login-modal.js"></script>