package com.itwill.project.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BestTopic {
	private Long sub_category_id;
	private String sub_category_name;
	private Long post_id;
	private String title;
	private Long view_count;
	private Long like_point;
	private Long comment_cnt;
	private String user_id;
	private String nickname;
	private String profile_url;
	private String created_time;
	private List<String> hashTag;
}
