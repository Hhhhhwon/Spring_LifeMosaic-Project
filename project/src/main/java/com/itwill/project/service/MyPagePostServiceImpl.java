package com.itwill.project.service;

import java.util.List;

///src/main/java/com/itwill/project/service/impl/PostServiceImpl.java
import org.springframework.stereotype.Service;

import com.itwill.project.domain.MyPagePost;
import com.itwill.project.domain.Post;
import com.itwill.project.repository.MyPagePostDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyPagePostServiceImpl implements MyPagePostService {
 private final MyPagePostDao myPagePostDao;

 @Override
 public List<MyPagePost> findPostsByUserId(String user_id) {
     return myPagePostDao.selectPostsByUserId(user_id);
 }
 
 public List<MyPagePost> getPostsWithCommentCount(String user_id) {
     return myPagePostDao.selectPostsWithCommentCount(user_id);
 }
 
}