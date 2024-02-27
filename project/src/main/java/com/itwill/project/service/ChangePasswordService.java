package com.itwill.project.service;

import com.itwill.project.dto.setting.PasswordChangeDto;

public interface ChangePasswordService {

	/**
	 * 사용자의 비밀번호를 변경합니다.
	 *
	 * @param userId 사용자 ID
	 * @param passwordChangeDto 비밀번호 변경 정보를 담은 DTO
	 * @return 비밀번호 변경 성공 여부를 반환합니다.
	 */
	boolean changePassword(String userId, PasswordChangeDto passwordChangeDto);

}