package com.itwill.project.dto.user;

import com.itwill.project.domain.User;

import lombok.Data;

@Data
public class UserSignInDto {
	private String user_id;
  private String nickname;
	private String password;
	
	public User toEntity() {
		return User.builder()
				.user_id(user_id)
				.nickname(nickname)
				.password(password)
				.build();
	}
}
