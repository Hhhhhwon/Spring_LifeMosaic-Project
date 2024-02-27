package com.itwill.project.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeeHashTag {
    
    private Long post_id;
    private Long tag_id;
    private String hashTag;
    
}
