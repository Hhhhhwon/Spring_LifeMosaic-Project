package com.itwill.project.repository;

import com.itwill.project.domain.VerificationCode;

public interface VerificationCodeRepository {
	void save(VerificationCode code);
	VerificationCode findByEmail(String email);

	void delete(VerificationCode storedCode);
}