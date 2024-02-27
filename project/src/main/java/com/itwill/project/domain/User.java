package com.itwill.project.domain;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 데이터베이스 USERS 테이블 모델(엔터티).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String user_id;
    private String nickname;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$")
    private String password; // 업데이트된 정규 표현식

    private String email;
    private Long point;
    private String profile_url;
}