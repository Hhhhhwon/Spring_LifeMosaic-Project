package com.itwill.project.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;


public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		// AJAX 요청 감지
		boolean isAjaxRequest = "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"));

		if (session == null || session.getAttribute("signedInUser") == null) {
			if (isAjaxRequest) {
				// AJAX 요청에 대해 401 상태 코드 반환
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
				return;
			} else {
				// 기존 리디렉션 로직: 로그인 페이지로 리디렉트
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/project/user/signin");
				return;
			}
		}
		// 로그인 상태라면 요청을 계속 진행합니다.
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 초기화 로직
	}

	@Override
	public void destroy() {
		// 필터 소멸 로직
	}
}