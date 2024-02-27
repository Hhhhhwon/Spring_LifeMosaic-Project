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
public class MyBookmarkListItemByPaging {
	private int row_num;
	private String bookmark_created_time;
	private long post_id;
	private String post_user_id;
	private String profile_url;
	private String post_user_nickname;
	private String post_title;
	private String post_content;
	private String post_time;
	private int post_sub_category_id;
	private String post_sub_category_name;
	private int post_view_count;
	private int post_like_count;
	private int post_comment_count;
	
	private List<String> hashTag;
	
}
