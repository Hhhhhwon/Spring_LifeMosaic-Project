package com.itwill.project.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

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
    //연수 코드 추가 - 해시태그 배열로 저장
    private List<String> hashTag;

}
