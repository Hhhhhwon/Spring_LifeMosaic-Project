package com.itwill.project.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.project.domain.CommentLike;
import com.itwill.project.domain.CommentLikeCheck;
import com.itwill.project.dto.like.CommentLikeDto;
import com.itwill.project.service.CommentLikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like/comment") // URL 경로 수정: "/api/like/comment"
public class CommentLikeRestController {
    
    private final CommentLikeService commentLikeService;
    
    @GetMapping("/count/{comment_id}") // URL 경로 수정: "/count/{comment_id}"
    public ResponseEntity<CommentLike> commentLikeCount(@PathVariable Long comment_id) {
        // 댓글 좋아요 수 조회 메서드
        log.debug("getLikeCount(comment_id = {})", comment_id);
        CommentLike commentLike = commentLikeService.commentLikeCount(comment_id);
        return ResponseEntity.ok(commentLike);
    }
    
    @PostMapping("/increase") // URL 경로 수정: "/increase"
    public ResponseEntity<Integer> commentLikeIncrease(@RequestBody CommentLikeDto dto) {
        // 댓글 좋아요 증가 메서드
        log.debug("commentLikeDto = {}", dto);
        
        int result = commentLikeService.commentLikeIncrease(dto.getComment_id());
        commentLikeService.likeCheck(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @PostMapping("/decrease") // URL 경로 수정: "/decrease"
    public ResponseEntity<Integer> commentDislike(@RequestBody CommentLikeDto dto) {
        // 댓글 싫어요 메서드
        log.debug("commentLikeDto = {}", dto);
        
        int result = commentLikeService.commentLikeDecrease(dto.getComment_id());
        commentLikeService.dislikeCheck(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @PostMapping("/cancel/like") // URL 경로 수정: "/cancel/like"
    public ResponseEntity<Integer> commentLikeCancel(@RequestBody CommentLikeDto dto) {
        // 댓글 좋아요 취소 메서드
        log.debug("commentLikeDto = {}", dto);
        
        int result = commentLikeService.commentLikeCancel(dto.getComment_id());
        commentLikeService.likeCheckCancel(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @PostMapping("/cancel/dislike") // URL 경로 수정: "/cancel/dislike"
    public ResponseEntity<Integer> commentDislikeCancel(@RequestBody CommentLikeDto dto) {
        // 댓글 싫어요 취소 메서드
        log.debug("commentLikeDto = {}", dto);
        
        int result = commentLikeService.commentDislikeCancel(dto.getComment_id());
        commentLikeService.dislikeCheckCancel(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @GetMapping("/check") // URL 경로 수정: "/check"
    public ResponseEntity<Integer> commentLikeCheck(@RequestParam("comment_id") Long comment_id, @RequestParam("user_id") String user_id) {
        // 댓글 좋아요 확인 메서드
        int likeCheck = commentLikeService.commentLikeCheck(comment_id, user_id);
        return ResponseEntity.ok(likeCheck);
    }
    
    @GetMapping("/detailcheck")
    public ResponseEntity<CommentLikeCheck> commentLikeDetailCheck(@RequestParam("comment_id") Long comment_id, @RequestParam("user_id") String user_id) {
        try {
            CommentLikeCheck commentLike = commentLikeService.commentLikeDetailCheck(comment_id, user_id);
            return ResponseEntity.ok(commentLike);
        } catch (Exception e) {
            // 예외 발생 시 적절한 응답 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/create/check") // URL 경로 수정: "/create/check"
    public ResponseEntity<Integer> makeCommentLikeCheck(@RequestBody CommentLikeDto dto) {
        // 댓글 좋아요 생성 메서드
        log.debug("CommentLikeDto = {}", dto);
        
        int result = commentLikeService.makeCommentLikeCheck(dto);
        
        return ResponseEntity.ok(result);
    }
}