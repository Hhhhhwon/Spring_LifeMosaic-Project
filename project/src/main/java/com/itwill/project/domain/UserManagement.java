package com.itwill.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//데이터베이스 USERS 테이블 모델(엔터티).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserManagement {
    private String user_id;
    private String password;
}