package com.itwill.project.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPostListItem {
	private int row_num;
	private String post_title;
	private String post_user_id;
	private String post_created_time;
	private int post_view_count;
	private String post_user_profile_url;
	private String post_nickname;
	private String post_subcategory;
	private int post_subcategory_id;
	private int post_id;
	private int post_like_count;
	private int comment_count;
	
	private List<String> hashTag;
}
