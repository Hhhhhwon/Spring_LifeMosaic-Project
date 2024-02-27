package com.itwill.project.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.project.domain.User;
import com.itwill.project.dto.user.UserRegisterDto;
import com.itwill.project.dto.user.UserSignInDto;
import com.itwill.project.repository.UserDao;
import com.itwill.project.repository.UserManagementDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserDao userDao;
	private final UserManagementDao userManagementDao;
	private final MailSendService mailSendService;
	// 사용자 아이디 중복 확인
	public boolean checkUserid(String user_id) {
		log.debug("checkUserid(user_id={})", user_id);

		User user = userDao.selectByUserid(user_id);
		return user == null;
	}

	// 닉네임 중복 확인
	public boolean checkNickname(String nickname) {
		log.debug("checkNickname(nickname={})", nickname);

		User user = userDao.selectByNickname(nickname);
		return user == null;
	}

	// 회원가입 처리
	public int create(UserRegisterDto dto) {
		log.debug("create(dto={})", dto);

		return userDao.insert(dto.toEntity());
	}

	// 로그인 처리
	// 로그인 처리
	public Map<String, Object> read(UserSignInDto dto) {
		log.debug("read(dto={})", dto);
		User user = userDao.selectByUseridAndPassword(dto);
		Map<String, Object> userData = new HashMap<>();

		if (user != null) {
			String storedPassword = userManagementDao.getPasswordByUserId(user.getUser_id());
			boolean isTemporary = isTemporaryPassword(storedPassword);

			userData.put("user", user);
			userData.put("isTemporaryPassword", isTemporary);
		}

		return userData;
	}

	// 임시 비밀번호 여부 확인 로직
	private boolean isTemporaryPassword(String password) {
		// 임시 비밀번호 판별 로직 구현
		// 예: password.startsWith("TEMP_")
		return password != null && password.startsWith("TEMP_");
	}

	// 이메일로 아이디 찾기
	public String findUserIdByEmail(String email) {
		return userManagementDao.findUserIdByEmail(email);
	}

	// 이메일을 통해 비밀번호 초기화 및 임시 비밀번호 발송
	@Transactional
	public void resetPasswordByEmail(String email) {
		String tempPassword = generateRandomPassword(8);
		String user_id = userManagementDao.findUserIdByEmail(email);

		// 사용자의 비밀번호를 임시 비밀번호로 업데이트
		userManagementDao.updatePasswordByUserId(user_id, tempPassword);

		// 이메일 서비스를 사용하여 임시 비밀번호 발송
		mailSendService.sendTempPasswordEmail(email, tempPassword);
	}

	// 임시 비밀번호 생성
	private String generateRandomPassword(int length) {
		// 암호에 사용할 문자 집합 정의
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		// 지정된 길이에 맞춰 무작위 문자 생성
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	// 비밀번호 변경
	public void updateEmailByUserId(String userId, String newEmail) {
		int result = userManagementDao.updateEmailByUserId(userId, newEmail);
		if (result != 1) {
			throw new RuntimeException("이메일 업데이트 실패: 사용자 ID = " + userId);
		}
		log.debug("이메일 업데이트 성공: 사용자 ID = {}, 새 이메일 = {}", userId, newEmail);
	}

	public boolean checkEmailExists(String email) {
		return userManagementDao.checkEmailExists(email) > 0;
	}

	public void updatePassword(String userId, String newPassword) {
		int result = userManagementDao.updatePasswordByUserId(userId, newPassword);
		if (result != 1) {
			throw new RuntimeException("비밀번호 업데이트 실패: 사용자 ID = " + userId);
		}
		log.debug("비밀번호 업데이트 성공: 사용자 ID = {}", userId);
	}
}