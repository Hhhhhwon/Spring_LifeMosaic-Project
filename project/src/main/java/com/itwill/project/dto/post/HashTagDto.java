package com.itwill.project.dto.post;

import java.time.LocalDateTime;

import com.itwill.project.domain.LeeHashTag;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HashTagDto {
    
    private Long post_id;
    private Long tag_id;
    private String hashTag;
    
    public static HashTagDto fromEntity(LeeHashTag leeHashTag) {
            
        return HashTagDto.builder()
                .post_id(leeHashTag.getPost_id())
                .tag_id(leeHashTag.getTag_id())
                .hashTag(leeHashTag.getHashTag())
                .build();
    }
}
