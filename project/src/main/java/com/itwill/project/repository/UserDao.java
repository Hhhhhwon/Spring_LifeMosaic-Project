package com.itwill.project.repository;

import com.itwill.project.domain.User;
import com.itwill.project.dto.user.UserSignInDto;

public interface UserDao {
    
    User selectByUserid(String user_id);
    User selectByNickname(String nickname);
    int insert(User user);
    User selectByUseridAndPassword(UserSignInDto user);
}