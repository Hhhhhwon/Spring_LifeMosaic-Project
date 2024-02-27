package com.itwill.project.domain;


import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchListItem {
	private Long sub_category_id;
	private String sub_category_name;
	private Long post_id;
	private String title;
	private String content;
	private String nickname;
	private String user_id;
	private String profile;
	private Long view_count;
	private Long like_point;
	private Long comment_cnt;
	private String created_time;
	
    private List<String> hashTag;

}
