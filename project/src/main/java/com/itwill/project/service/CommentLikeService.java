package com.itwill.project.service;

import org.springframework.stereotype.Service;

import com.itwill.project.domain.CommentLike;
import com.itwill.project.domain.CommentLikeCheck;
import com.itwill.project.dto.like.CommentLikeDto;
import com.itwill.project.repository.CommentLikeDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
/**
 * 댓글 좋아요에 대한 서비스를 제공하는 클래스
 */
public class CommentLikeService {
    
    private final CommentLikeDao commentLikeDao; // CommentLikeDao 인터페이스를 주입받음

    public int commentLikeIncrease(Long comment_id) {
        // 댓글의 좋아요 수를 증가시키는 메소드
        int result = commentLikeDao.commentLikeIncrease(comment_id);
        return result;
    }
    
    public int commentLikeDecrease(Long comment_id) {
        // 댓글의 좋아요 수를 감소시키는 메소드
        int result = commentLikeDao.commentLikeDecrease(comment_id);
        return result;
    }

    public CommentLike commentLikeCount(Long comment_id) {
        // 댓글의 좋아요 수를 조회하는 메소드
        CommentLike commentLike = commentLikeDao.likeCount(comment_id);
        return commentLike;
    }

    // 댓글에 대한 좋아요/싫어요 상태를 확인하는 메소드
    public int commentLikeCheck(Long comment_id, String user_id) {
        int likeCheck = commentLikeDao.commentLikeCheck(comment_id, user_id);
        log.debug("checkResult = {}", likeCheck);
        return likeCheck;
    }

    public int makeCommentLikeCheck(CommentLikeDto dto) {
        // 댓글에 대한 좋아요를 생성하는 메소드
        int result = commentLikeDao.makeCommentLikeCheck(dto);
        return result;
    }

    public CommentLikeCheck commentLikeDetailCheck(Long comment_id, String user_id) {
        // 댓글의 좋아요 상세 정보를 확인하는 메소드
        CommentLikeCheck commentLike = commentLikeDao.commentLikeDetailCheck(comment_id, user_id);
        return commentLike;
    }

    public void likeCheck(CommentLikeDto dto) {
        // 댓글에 대한 좋아요를 체크하는 메소드
        commentLikeDao.likeCheck(dto);
    }

    public void dislikeCheck(CommentLikeDto dto) {
        // 댓글에 대한 싫어요를 체크하는 메소드
        commentLikeDao.dislikeCheck(dto);
    }

    public int commentLikeCancel(Long comment_id) {
        // 댓글의 좋아요를 취소하는 메소드
        int result = commentLikeDao.commentLikeCancel(comment_id);
        return result;
    }

    public void likeCheckCancel(CommentLikeDto dto) {
        // 댓글의 좋아요 체크를 취소하는 메소드
        commentLikeDao.likeCheckCancel(dto);
    }

    public int commentDislikeCancel(Long comment_id) {
        // 댓글의 싫어요를 취소하는 메소드
        int result = commentLikeDao.commentDislikeCancel(comment_id);
        return result;
    }

    public void dislikeCheckCancel(CommentLikeDto dto) {
        // 댓글의 싫어요 체크를 취소하는 메소드
        commentLikeDao.dislikeCheckCancel(dto);
    }
}
