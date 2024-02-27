package com.itwill.project.repository;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.dto.like.PostLikeDto;

public interface BookmarkDao {

    int BookmarkCheck(@Param("post_id") Long post_id,@Param("user_id") String user_id);

    int BookmarkAdd(PostLikeDto dto);

    int CancelBookmark(PostLikeDto dto);
    

}
