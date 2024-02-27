package com.itwill.project.dto.post;

import java.util.List;

import com.itwill.project.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostModifyDto {
    
    private String title;
    private String content;
    private Long post_id;
    private Long sub_category_id;
    
    //연수 코드 추가 - 해시태그 배열로 저장
    private List<String> hashTag;
    
    public Post ToPost() {
        return Post.builder()
                .title(title)
                .content(content)
                .post_id(post_id)
                .hashTag(hashTag)
                .build();
    }
}
