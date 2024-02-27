package com.itwill.project.repository;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.CommentLike;
import com.itwill.project.domain.CommentLikeCheck;
import com.itwill.project.domain.PostLike;
import com.itwill.project.domain.PostLikeCheck;
import com.itwill.project.dto.like.CommentLikeDto;
import com.itwill.project.dto.like.PostLikeDto;

/**
 * 댓글 좋아요 관련 데이터베이스 접근을 위한 DAO 인터페이스
 */
public interface CommentLikeDao {

    /**
     * 댓글의 좋아요 수를 증가시키는 메서드
     */
    int commentLikeIncrease(Long comment_id);
    
    /**
     * 댓글의 좋아요 수를 감소시키는 메서드
     */
    int commentLikeDecrease(Long comment_id);

    /**
     * 댓글의 좋아요 수를 조회하는 메서드
     */
    CommentLike likeCount(Long comment_id);

    /**
     * 댓글 좋아요를 확인하는 메서드
     */
    int commentLikeCheck(@Param("comment_id") Long comment_id, @Param("user_id") String user_id);

    /**
     * 댓글의 좋아요를 생성하는 메서드
     */
    int makeCommentLikeCheck(CommentLikeDto dto);

    /**
     * 댓글 좋아요 상세 정보를 확인하는 메서드
     */
    CommentLikeCheck commentLikeDetailCheck(@Param("comment_id") Long comment_id, @Param("user_id") String user_id);

    /**
     * 댓글에 대한 좋아요를 체크하는 메서드
     */
    void likeCheck(CommentLikeDto dto);

    /**
     * 댓글에 대한 싫어요를 체크하는 메서드
     */
    void dislikeCheck(CommentLikeDto dto);

    /**
     * 댓글의 좋아요를 취소하는 메서드
     */
    int commentLikeCancel(Long comment_id);

    /**
     * 댓글의 좋아요 체크를 취소하는 메서드
     */
    void likeCheckCancel(CommentLikeDto dto);

    /**
     * 댓글의 싫어요를 취소하는 메서드
     */
    int commentDislikeCancel(Long comment_id);

    /**
     * 댓글의 싫어요 체크를 취소하는 메서드
     */
    void dislikeCheckCancel(CommentLikeDto dto);
}
