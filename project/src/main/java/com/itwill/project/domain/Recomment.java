package com.itwill.project.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recomment {
    
    private Long comment_id;
    private Long re_comment_id;
    private String recomment_content;
    private String user_id;
    private String profile_url;
    private String recomment_created_time;
    private String recomment_modified_time;
    private String nickname;
    private Long like_point;
    private Long dislike_point;
}
