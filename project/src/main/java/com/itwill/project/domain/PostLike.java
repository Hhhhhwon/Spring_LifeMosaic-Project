package com.itwill.project.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLike {
    
    private Long post_id;
    private Long like_point;
    private Long dislike_point;
}
