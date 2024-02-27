package com.itwill.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.project.domain.PostLike;
import com.itwill.project.domain.PostLikeCheck;
import com.itwill.project.dto.like.PostLikeDto;
import com.itwill.project.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeRestController {
    
    private final LikeService likeService;
    
    @GetMapping("/get/{post_id}")
    public ResponseEntity<PostLike> postLikeCount(@PathVariable Long post_id) {
        log.debug("getLikeCount(post_id = {})", post_id);
        PostLike postLike = likeService.likeCount(post_id);
        return ResponseEntity.ok(postLike);
    }
    
    @PostMapping("/increase")
    public ResponseEntity<Integer> postLike(@RequestBody PostLikeDto dto) {
        log.debug("postLikeDto = {}", dto);
        
        int result = likeService.postLikeIncrease(dto.getPost_id());
        likeService.likeCheck(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @PostMapping("/decrease")
    public ResponseEntity<Integer> postDisLike(@RequestBody PostLikeDto dto) {
        log.debug("postLikeDto = {}", dto);
        
        int result = likeService.postLikeDecrease(dto.getPost_id());
        likeService.dislikeCheck(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @PostMapping("/like/cancel")
    public ResponseEntity<Integer> postLikeCancel(@RequestBody PostLikeDto dto) {
        log.debug("postLikeDto = {}", dto);
        
        int result = likeService.postLikeCancel(dto.getPost_id());
        likeService.likeCheckCancel(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @PostMapping("/dislike/cancel")
    public ResponseEntity<Integer> postDisLikeCancel(@RequestBody PostLikeDto dto) {
        log.debug("postLikeDto = {}", dto);
        
        int result = likeService.postDislikeCancel(dto.getPost_id());
        likeService.dislikeCheckCancel(dto);
        
        return ResponseEntity.ok(result);  
    }
    
    @GetMapping("/check")
    public ResponseEntity<Integer> postLikeCheck(@RequestParam("post_id") Long post_id, @RequestParam("user_id") String user_id) {
        
        int likeCheck = likeService.postLikeCheck(post_id, user_id);
        
        return ResponseEntity.ok(likeCheck);
    }
    
    @GetMapping("/detailcheck")
    public ResponseEntity<PostLikeCheck> postLikeDetailCheck(@RequestParam("post_id") Long post_id, @RequestParam("user_id") String user_id) {
        
        PostLikeCheck postLike = likeService.postLikeDetailCheck(post_id, user_id);
        
        return ResponseEntity.ok(postLike);
    }
    
    @PostMapping("/make/check")
    public ResponseEntity<Integer> makePostLikeCheck(@RequestBody PostLikeDto dto) {
        log.debug("PostLikeDto = {}", dto);
        
        int result = likeService.makePostLikeCheck(dto);
        
        return ResponseEntity.ok(result);
    }
}
