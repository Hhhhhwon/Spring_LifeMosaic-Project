package com.itwill.project.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentLikeCheck {
    
    private String user_id;
    private Long comment_id;
    private int like_check;
    private int dislike_check;
}
