package com.itwill.project.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.project.domain.VerificationCode;
import com.itwill.project.repository.UserManagementDao;
import com.itwill.project.repository.VerificationCodeRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
public class MailSendService {
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private UserManagementDao userManagementDao;

	@Autowired
	private VerificationCodeRepository verificationCodeRepository; // 인증 코드를 저장하는 저장소

	// 임시 비밀번호 이메일 전송
	public void sendTempPasswordEmail(String email, String tempPassword) {
		String setFrom = "dlvlfdn567@gmail.com"; // 발신자 이메일 주소
		String toMail = email;
		String title = "임시 비밀번호 발송"; // 이메일 제목
		String content =
			"귀하의 임시 비밀번호는 " + tempPassword + " 입니다.<br>" +
				"로그인 후 비밀번호를 변경해주세요."; // 이메일 내용

		mailSend(setFrom, toMail, title, content);
	}

	// 이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}




	// 이메일 인증 메서드
	public void sendVerificationEmail(String email, HttpSession session) {
		String verificationCode = generateVerificationCode();
		String content = "이메일 주소를 인증하려면 다음 링크를 클릭하세요: "
			+ "<a href='http://localhost:8081/project/setting/verifyEmail?code=" + verificationCode + "&email=" + email
			+ "'>이메일 인증</a>";

		// 이메일 전송
		mailSend("noreply@example.com", email, "이메일 인증", content);

		// 인증 코드 저장
		VerificationCode newCode = new VerificationCode(email, verificationCode);
		verificationCodeRepository.save(newCode);

		// 인증된 이메일 주소를 세션에 저장
		session.setAttribute("verifiedEmail", email);
	}

	// VerificationCodeRepository와 VerificationCode 클래스는 추가 구현이 필요합니다.

	// 임시 비밀번호 생성
	private String generateRandomPassword(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	private String generateVerificationCode() {
		// 인증 코드 길이
		int length = 8;

		// 사용할 문자 집합 (예: 대문자, 소문자, 숫자)
		String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		// StringBuilder를 사용하여 랜덤 문자열 생성
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(charSet.length());
			char randomChar = charSet.charAt(index);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	public String joinEmail(String email) {
		// 이메일 인증 코드 생성
		String verificationCode = generateVerificationCode();

		// 이메일 인증 코드를 이메일로 전송
		String content = "귀하의 이메일 인증 코드는 " + verificationCode + " 입니다.";
		mailSend("noreply@example.com", email, "이메일 인증 코드", content);

		// 생성한 이메일 인증 코드 반환
		return verificationCode;
	}

	public void updateEmail(String user_id, String newEmail) {
		log.debug("updateEmail 호출: user_id={}, newEmail={}", user_id, newEmail);
		int result = userManagementDao.updateEmail(user_id, newEmail);
		// 로그로 결과 확인
		log.debug("updateEmail 결과: {}", result);
	}
}