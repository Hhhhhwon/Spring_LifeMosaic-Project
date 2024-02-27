package com.itwill.project.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentLike {
    
    private Long comment_id;
    private Long like_point;
    private Long dislike_point;
}
