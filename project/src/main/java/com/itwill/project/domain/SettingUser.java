package com.itwill.project.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettingUser {
	private String user_id;
	private String password;
	private String nickname;
	private String email;
	private int point;
	private String profile_url;
	
}
