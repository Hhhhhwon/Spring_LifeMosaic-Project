package com.itwill.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.LeeHashTag;

public interface HashtagDao {

    List<LeeHashTag> getAllHashTag(@Param("post_id")Long post_id);
    
}
