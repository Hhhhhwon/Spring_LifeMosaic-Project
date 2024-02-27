package com.itwill.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.project.dto.like.PostLikeDto;
import com.itwill.project.service.BookmarkService;
import com.itwill.project.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookmarkRestController {
    
    private final BookmarkService bookmarkService;
    
    @GetMapping("/check")
    public ResponseEntity<Integer> postLikeCheck(@RequestParam("post_id") Long post_id, @RequestParam("user_id") String user_id) {
         
        int likeCheck = bookmarkService.BookmarkCheck(post_id, user_id);
        
        return ResponseEntity.ok(likeCheck);
    }
    
    @PostMapping("")
    public ResponseEntity<Integer> BookmarkAdd(@RequestBody PostLikeDto dto) {
        log.debug("PostLikeDto = {}", dto);
        
        int result = bookmarkService.BookmarkAdd(dto);
        
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/cancel")
    public ResponseEntity<Integer> Cancelbookmark(@RequestBody PostLikeDto dto) {
        log.debug("PostLikeDto = {}", dto);
        
        int result = bookmarkService.CancelBookmark(dto);
        
        return ResponseEntity.ok(result);
    
    }
}