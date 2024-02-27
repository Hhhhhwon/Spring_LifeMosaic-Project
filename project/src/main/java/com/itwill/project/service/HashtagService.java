package com.itwill.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.project.domain.LeeHashTag;
import com.itwill.project.dto.post.HashTagDto;
import com.itwill.project.repository.HashtagDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HashtagService {
    
    private final HashtagDao hashtagDao;

    public List<HashTagDto> getAllHashtag(Long post_id) {
        
        List<LeeHashTag> list = hashtagDao.getAllHashTag(post_id);
        
        return list.stream().map(HashTagDto::fromEntity).toList();
    }
    
}
