package com.itwill.project.dto.post;

import java.time.LocalDateTime;

import com.itwill.project.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostListItemDto {

    private Long rnum;
    private String user_id;
    private Long post_id;
    private String title;
    private String content;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    private Long sub_category_id;
    private String image_path;
    private Long view_count;
    private String nickname;
    private String profile_url;
    private Long like_point;
    private Long comment_count;
    
    public static PostListItemDto fromEntity(Post post) {
        
        return PostListItemDto.builder()
                .user_id(post.getUser_id())
                .post_id(post.getPost_id())
                .title(post.getTitle())
                .content(post.getContent())
                .created_time(post.getCreated_time())
                .sub_category_id(post.getSub_category_id())
                .image_path(post.getImage_path())
                .view_count(post.getView_count())
                .nickname(post.getNickname())
                .profile_url(post.getProfile_url())
                .like_point(post.getLike_point())
                .comment_count(post.getComment_count())
                .build();
    }
}
