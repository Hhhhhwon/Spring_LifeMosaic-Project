package com.itwill.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopWriter {
	private String user_id;
	private String nickname;
	private String profile_url;
	private Long cnt;
}
