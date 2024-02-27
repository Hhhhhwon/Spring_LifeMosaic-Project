package com.itwill.project.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.project.dto.post.HashTagDto;
import com.itwill.project.service.HashtagService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("post/api/hashtag")
public class HashtagRestController {
    
    private final HashtagService hashtagService;
    
    @GetMapping("/{post_id}")
    public ResponseEntity<List<HashTagDto>> getAllHashtag(@PathVariable Long post_id) {
        
        List<HashTagDto> result = hashtagService.getAllHashtag(post_id);
        log.debug("post_id = {}", post_id);
        
        return ResponseEntity.ok(result);
    }
}
