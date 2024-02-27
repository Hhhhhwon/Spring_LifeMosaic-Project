package com.itwill.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.Comment;
import com.itwill.project.domain.Recomment;
import com.itwill.project.dto.comment.CommentRegisterDto;

public interface CommentDao {

    Long commentCheck(@Param("post_id")Long post_id);

    List<Comment> getAllComment(@Param("post_id")Long post_id,@Param("commentEnd") Long commentEnd,@Param("commentStart") Long commentStart);

    int insert(Comment comment);

    int update(Comment comment);

    int delete(@Param("comment_id")Long comment_id);

    List<Recomment> getRecomment(@Param("comment_id") Long comment_id);

    int insertRecomment(Recomment recomment);

    int deleteRecomment(@Param("re_comment_id")Long re_comment_id);
    
    int updateRecomment(Recomment recomment);
    
}
