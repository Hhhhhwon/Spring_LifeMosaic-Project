package com.itwill.project.repository;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.PostLike;
import com.itwill.project.domain.PostLikeCheck;
import com.itwill.project.dto.like.PostLikeDto;

public interface LikeDao {

    int postLikeIncrease(Long post_id);
    
    int postLikeDecrease(Long post_id);

    PostLike likeCount(Long post_id);

    int postLikeCheck(@Param("post_id")Long post_id,@Param("user_id") String user_id);

    int makePostLikeCheck(PostLikeDto dto);

    PostLikeCheck postLikeDetailCheck(@Param("post_id")Long post_id,@Param("user_id") String user_id);

    void likeCheck(PostLikeDto dto);

    void dislikeCheck(PostLikeDto dto);

    int postLikeCancel(Long post_id);

    void likeCheckCancel(PostLikeDto dto);

    int postDislikeCancel(Long post_id);

    void dislikeCheckCancel(PostLikeDto dto);


}
