package com.itwill.project.service;

///src/main/java/com/itwill/project/service/PostService.java
import java.util.List;

import com.itwill.project.domain.MyPagePost;

public interface MyPagePostService {
 List<MyPagePost> findPostsByUserId(String user_id);
 
 List<MyPagePost> getPostsWithCommentCount(String user_id);
}