package com.itwill.project.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.itwill.project.domain.VerificationCode;

@Repository
public class InMemoryVerificationCodeRepository implements VerificationCodeRepository {
	private final Map<String, VerificationCode> codeMap = new HashMap<>();

	@Override
	public void save(VerificationCode code) {
		codeMap.put(code.getEmail(), code);
	}

	@Override
	public VerificationCode findByEmail(String email) {
		return codeMap.get(email);
	}

	@Override
	public void delete(VerificationCode storedCode) {
		codeMap.remove(storedCode.getEmail());
	}
}
