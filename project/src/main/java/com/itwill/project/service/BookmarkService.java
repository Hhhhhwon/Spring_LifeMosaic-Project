package com.itwill.project.service;

import org.springframework.stereotype.Service;

import com.itwill.project.dto.like.PostLikeDto;
import com.itwill.project.repository.BookmarkDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkService {
    
    private final BookmarkDao bookmarkDao;

    public int BookmarkCheck(Long post_id, String user_id) {
        
        int result = bookmarkDao.BookmarkCheck(post_id, user_id);
        
        return result;
    }

    public int BookmarkAdd(PostLikeDto dto) {
        
        int result = bookmarkDao.BookmarkAdd(dto);
        
        return result;
    }

    public int CancelBookmark(PostLikeDto dto) {
        
        int result = bookmarkDao.CancelBookmark(dto);
        
        return result;
    }
    
    
}
