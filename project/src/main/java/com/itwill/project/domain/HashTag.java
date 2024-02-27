package com.itwill.project.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HashTag {
	private Long post_id;
	private Long tag_id;
	
    //연수 코드 추가 - 해시태그 배열로 저장
    private List<String> hashTag;
}
