package com.itwill.project.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberPostPageDto {
	private String nickname;
	private int startNum;
	private int endNum;
}
