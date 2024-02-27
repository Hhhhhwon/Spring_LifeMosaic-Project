package com.itwill.project.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.project.domain.Comment;
import com.itwill.project.domain.Criteria;
import com.itwill.project.domain.Recomment;
import com.itwill.project.dto.comment.CommentRegisterDto;
import com.itwill.project.dto.comment.CommentUpdateDto;
import com.itwill.project.dto.comment.RecommentRegisterDto;
import com.itwill.project.dto.comment.RecommentUpdateDto;
import com.itwill.project.dto.post.PageMakerDto;
import com.itwill.project.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그를 위한 롬복 어노테이션
@RestController // REST API를 처리하는 컨트롤러임을 나타냄
@RequiredArgsConstructor // 생성자 주입을 위한 롬복 어노테이션
@RequestMapping("/api/comment") // 컨트롤러의 기본 URL 매핑
public class CommentRestController {

    private final CommentService commentService; // CommentService 주입
    
    // 댓글 개수 확인
    @GetMapping("/check")
    public ResponseEntity<Long> commentCheck(@RequestParam("post_id") Long postId) {
        log.debug("commentCheck(postId = {})", postId);
        
        // Service를 통해 해당 게시물의 댓글 개수를 확인하고 반환
        Long result = commentService.commentCheck(postId);
        
        return ResponseEntity.ok(result);
    }
    
    // 페이징된 댓글 목록 조회
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getAllComment(
            Criteria cri, // 페이징 관련 조건을 담은 객체
            @RequestParam("post_id") Long postId,
            @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum
    ) {
        log.debug("getAllComment(postId = {})", postId);
        
        // 페이징 정보 계산
        Long commentEnd = pageNum * 10;
        Long commentStart = ((pageNum - 1) * 10) + 1;
        Long total = commentService.commentCheck(postId);
        
        // 해당 게시물의 페이징된 댓글 목록 조회
        List<Comment> comments = commentService.getAllComment(postId, commentEnd, commentStart);
        
        // 페이징 관련 정보 세팅
        cri.setAmount(10);
        cri.setPageNum(pageNum);
        
        // 페이징 정보를 담은 DTO 생성
        PageMakerDto pageMake = new PageMakerDto(cri, total);
        
        // 댓글 목록과 페이징 정보를 Response로 반환
        Map<String, Object> response = new HashMap<>();
        response.put("comments", comments);
        response.put("pageMaker", pageMake);
        
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(response);
    }
    
    // 댓글 등록
    @PostMapping
    public ResponseEntity<Integer> registerComment(@RequestBody CommentRegisterDto dto) {
        log.debug("CommentRegisterDto = {}", dto);
        // 받은 DTO를 이용하여 댓글 생성하고 결과 반환
        int result = commentService.create(dto);
        
        return ResponseEntity.ok(result);
    }
    
    // 댓글 수정
    @PutMapping("/update")
    public ResponseEntity<Integer> updateComment(@RequestBody CommentUpdateDto dto) {
        log.debug("CommentUpdateDto = {}", dto);
        // 받은 DTO를 이용하여 댓글 수정하고 결과 반환
        int result = commentService.update(dto);
        
        return ResponseEntity.ok(result);
    }
    
 // 댓글 삭제
    @DeleteMapping("/{comment_id}") 
    public ResponseEntity<Integer> deleteComment(@PathVariable("comment_id") Long commentId) {
        log.debug("deleteCommentId = {}", commentId);
        // 해당 댓글 ID를 이용하여 댓글 삭제하고 결과 반환
        int result = commentService.delete(commentId);
        
        return ResponseEntity.ok(result);
    }
    
    // 대댓글 조회
    @GetMapping("/recomment")
    public ResponseEntity<List<Recomment>> getRecommentByCommentId (@RequestParam("comment_id") Long commentId) {
        log.debug("commentId = {}", commentId);
        
        // 해당 댓글의 대댓글 목록 조회하여 반환
        List<Recomment> recomments = commentService.getRecomment(commentId);
        
        return ResponseEntity.ok(recomments);
    }
    
    // 대댓글 등록
    @PostMapping("/recomment")
    public ResponseEntity<Integer> registerRecomment(@RequestBody RecommentRegisterDto dto) {
        log.debug("RecommentRegisterDto = {}", dto);
        
        // 받은 DTO를 이용하여 대댓글 생성하고 결과 반환
        int result = commentService.createRecomment(dto);
        
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/recomment/{re_comment_id}")
    public ResponseEntity<Integer> deleteRecomment(@PathVariable Long re_comment_id) {
        log.debug("deletere_comment_id = {}", re_comment_id);
        // 해당 대댓글 ID를 이용하여 대댓글 삭제하고 결과 반환
        int result = commentService.deleteRecomment(re_comment_id);
        
        return ResponseEntity.ok(result);
    }
    
 // 대댓글 수정
    @PutMapping("/recomment/{re_comment_id}")
    public ResponseEntity<Integer> updateRecomment(@RequestBody RecommentUpdateDto dto) {
        log.debug("RecommentUpdateDto = {}", dto);
        int result = commentService.updateRecomment(dto); // 서비스의 메소드 호출
        
        return ResponseEntity.ok(result);
    }
}
