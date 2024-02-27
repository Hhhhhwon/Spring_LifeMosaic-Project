package com.itwill.project.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.project.domain.User;
import com.itwill.project.dto.user.UserRegisterDto;
import com.itwill.project.dto.user.UserSignInDto;
import com.itwill.project.service.MailSendService;
import com.itwill.project.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@SessionAttributes("preLoginUrl") // 세션에 preLoginUrl을 저장하기 위해 사용
public class UserController {

	private final UserService userService;
	private final MailSendService mailSendService;

	@GetMapping("/checkLoginStatus")
	public ResponseEntity<?> checkLoginStatus(HttpSession session) {
		if (session != null && session.getAttribute("signedInUser") != null) {
			// 로그인 상태인 경우
			return ResponseEntity.ok().build();
		} else {
			// 로그인 상태가 아닌 경우
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	// 회원가입 페이지 렌더링
	@GetMapping("/signup")
	public void signup() {
		log.debug("GET - signup()");
	}

	// 회원가입 처리
	@PostMapping("/signup")
	public String signup(@ModelAttribute UserRegisterDto dto) {
		log.debug("POST - signup(dto={})", dto);
		userService.create(dto);
		return "redirect:/user/signin";
	}

	// 로그인 페이지 렌더링
	@GetMapping("/signin")
	public void signin() {
		log.debug("GET - signIn()");
	}

	// 로그인 처리
	@PostMapping("/signin")
	public String signin(@ModelAttribute UserSignInDto dto, HttpSession session,
		@RequestParam(required = false) String preLoginUrl,
		RedirectAttributes redirectAttributes) {
		log.debug("POST - signin(dto={} session={}", dto, session);
		log.debug("Received preLoginUrl: {}", preLoginUrl);

		Map<String, Object> userData = userService.read(dto);
		User user = (User) userData.get("user");

		if (user != null) {
			log.debug("Login successful. Redirecting to: {}", preLoginUrl != null ? preLoginUrl : "/");
			// 임시 비밀번호인 경우 비밀번호 변경 페이지로 리다이렉트
			if ((Boolean) userData.get("isTemporaryPassword")) {
				return "redirect:/user/changePassword";
			}

			// 세션에 사용자 정보 설정
			session.setAttribute("signedInUser", user.getUser_id());
			session.setAttribute("userProfileUrl", user.getProfile_url());

			// 로그인 전 페이지로 리다이렉트
			log.debug("preLoginUrl: {}", preLoginUrl);
			if (preLoginUrl != null && !preLoginUrl.isEmpty()) {
				return "redirect:" + preLoginUrl;
			} else {
				return "redirect:/"; // preLoginUrl이 없으면 홈 페이지로 리다이렉트
			}
		} else {
			// 로그인 실패 시, 로그인 페이지로 리다이렉트하고 실패 메시지 표시
			log.debug("Login failed. Redirecting to login page.");
			redirectAttributes.addFlashAttribute("loginFailedMessage", "아이디와 패스워드를 확인하세요...");
			return "redirect:/user/signin";
		}
	}

	// 로그아웃 처리
	@GetMapping("/signout")
	public String signout(HttpSession session) {
		log.debug("signout(session={})", session);
		session.removeAttribute("signedInUser");
		session.removeAttribute("userProfileUrl");
		session.invalidate();
		return "redirect:/user/signin";
	}

	// 사용자 ID 중복 확인
	@GetMapping("/checkid")
	@ResponseBody
	public ResponseEntity<String> checkId(@RequestParam(name = "user_id") String user_id) {
		log.debug("checkId(user_id={})", user_id);
		boolean result = userService.checkUserid(user_id);
		return result ? ResponseEntity.ok("Y") : ResponseEntity.ok("N");
	}

	// 닉네임 중복 확인
	@GetMapping("/checknickname")
	@ResponseBody
	public ResponseEntity<String> checknickname(@RequestParam(name = "nickname") String nickname) {
		log.debug("checknickname(nickname={})", nickname);
		boolean result = userService.checkNickname(nickname);
		return result ? ResponseEntity.ok("Y") : ResponseEntity.ok("N");
	}

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(@RequestParam(name = "email") String email) {
		log.debug("이메일 인증 요청이 들어옴! 이메일 인증 이메일 : {}", email);
		return mailSendService.joinEmail(email);
	}

	// 아이디 찾기 페이지 렌더링
	@GetMapping("/findUserId")
	public void findUserId() {
		log.debug("GET - findUserId()");
	}

	// 아이디 찾기 요청 처리 (JSON 응답)
	@PostMapping("/findUserId")
	@ResponseBody
	public ResponseEntity<?> findUserId(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		String user_id = userService.findUserIdByEmail(email); // 'userId'를 'user_id'로 변경

		Map<String, Object> response = new HashMap<>();
		response.put("user_id", user_id); // 'userId'를 'user_id'로 변경
		return ResponseEntity.ok(response);
	}

	// 비밀번호 찾기 페이지 렌더링
	@GetMapping("/findPassword")
	public void findPassword() {
		log.debug("GET - findPassword()");
	}

	// 비밀번호 찾기 요청 처리 (JSON 응답)
	@PostMapping("/findPassword")
	@ResponseBody
	public ResponseEntity<?> findPassword(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		userService.resetPasswordByEmail(email);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "임시 비밀번호가 이메일로 전송되었습니다.");
		return ResponseEntity.ok(response);
	}

	// 비밀번호 변경 페이지 렌더링
	@GetMapping("/changePassword")
	public String showChangePasswordForm() {
		return "user/changePassword";
	}

	// 비밀번호 변경 처리
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("newPassword") String newPassword, HttpSession session) {
		String user_id = (String) session.getAttribute("signedInUser");
		userService.updatePassword(user_id, newPassword);
		return "redirect:/user/signin";
	}
}