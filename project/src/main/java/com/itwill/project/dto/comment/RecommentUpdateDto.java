package com.itwill.project.dto.comment;

import com.itwill.project.domain.Recomment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class RecommentUpdateDto {
	
	private Long re_comment_id;
    private String rtext;
    
    public Recomment toEntity() {
        return Recomment.builder()
                .re_comment_id(re_comment_id)
                .recomment_content(rtext)
                .build();
    }

}
