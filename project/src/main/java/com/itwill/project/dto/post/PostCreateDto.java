package com.itwill.project.dto.post;

import java.util.List;

import com.itwill.project.domain.Post;

import lombok.Data;

@Data
public class PostCreateDto {
    
    private String title;
    private String content;
    private String user_id;
    private Long sub_category_id;
    
    //연수 코드 추가 - 해시태그 배열로 저장
    private List<String> hashTag;
    
    public Post toEntity() {
        
        return Post.builder()
                .title(title)
                .content(content)
                .user_id(user_id)
                .sub_category_id(sub_category_id)
                .hashTag(hashTag)
                .build();
    }
}
