package com.itwill.project.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    
    private Long rnum;
    private Long comment_id;
    private String comment_content;
    private String user_id;
    private Long post_id;
    private String comment_created_time;
    private String comment_modified_time;
    private String nickname;
    private String profile_url;
    private Long like_point;
    private Long dislike_point;
}
