package com.itwill.project.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 데이터베이스 USERS 테이블 모델(엔터티).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPagePost {
	private String user_id;        // USER_ID 컬럼, VARCHAR2(50 BYTE)
    private Long post_id;          // POST_ID 컬럼, NUMBER(10,0)
    private String title;         // TITLE 컬럼, VARCHAR2(1000 CHAR)
    private String content;       // CONTENT 컬럼, VARCHAR2(4000 CHAR)
    private String created_time; // CREATED_TIME 컬럼, TIMESTAMP(6)
    private String modified_time; // MODIFIED_TIME 컬럼, TIMESTAMP(6)
    private Long sub_category_id;   // SUB_CATEGORY_ID 컬럼, NUMBER(10,0)
    private String sub_category_name;
    private String image_path;     // IMAGE_PATH 컬럼, VARCHAR2(500 CHAR)
    private Long view_count;       // VIEW_COUNT 컬럼, NUMBER(10,0)
    private Long comment_count;
    private Long like_point;
    
    private List<String> hashTag;
}