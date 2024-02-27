package com.itwill.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.HashTag;
import com.itwill.project.domain.Post;
import com.itwill.project.domain.PostDetail;

public interface PostDao {
    

    List<Post> selectOrderByDesc(@Param("pageEnd") Long pageEnd,@Param("pageStart") Long pageStart);
    
    List<Post> selectBySubCategoryIdOrderByDesc (@Param("subCategoryId") Long subCategoryId, @Param("pageEnd") Long pageEnd,@Param("pageStart") Long pageStart);
    
    Long postCount();
    
    Long postCountBySubCategoryId(@Param("subCategoryId") Long subCategoryId);
    
    PostDetail selectById(Long post_id);
    
    int insert(Post post);
    
    int update(Post post);
    
    int delete(Long id);

    int viewCountIncrease(Long post_id);

    //태그 관련 dao
    String selectHashTag(String tagname);
    
    int insertHashTag(String tagname);
    
    int insertsPostHashTag(HashTag tag);
    
    int deletePostHash(Long post_id);
    
    List<String> selectHashtagByPostid(Long post_id);
    
    List<String> selectTagList(String value);
}
