package com.itwill.project.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bookmark {
    
    private Long post_id;
    private String user_id;
    private LocalDateTime created_time;
    
}
