package com.itwill.project.dto.user;

import com.itwill.project.domain.User;

import lombok.Data;

// 회원가입 정보를 저장하는 DTO
@Data
public class UserRegisterDto {
    private String user_id;
    private String nickname;
    private String password;
    private String email;
    
    // DTO의 필드 값들을 사용해서 엔터티 객체를 생성하고 리턴.
    public User toEntity() {
        return User.builder()
                .user_id(user_id)
                .nickname(nickname)
                .password(password)
                .email(email)
                .build();
    }

}