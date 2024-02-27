package com.itwill.project.dto.comment;

import com.itwill.project.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class CommentUpdateDto {

    private Long comment_id;
    private String ctext;
    
    public Comment toEntity() {
        return Comment.builder()
                .comment_id(comment_id)
                .comment_content(ctext)
                .build();
    }
}
