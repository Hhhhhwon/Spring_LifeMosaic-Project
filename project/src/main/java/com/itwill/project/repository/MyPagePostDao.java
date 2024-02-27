package com.itwill.project.repository;

import java.util.List;

import com.itwill.project.domain.MyPagePost;
import com.itwill.project.domain.Post;

public interface MyPagePostDao {
 List<MyPagePost> selectPostsByUserId(String user_id);
 
 List<MyPagePost> selectPostsWithCommentCount(String user_id);
}