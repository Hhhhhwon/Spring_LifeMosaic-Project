package com.itwill.project.service;

import org.springframework.stereotype.Service;

import com.itwill.project.domain.PostLike;
import com.itwill.project.domain.PostLikeCheck;
import com.itwill.project.dto.like.PostLikeDto;
import com.itwill.project.repository.LikeDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    
    private final LikeDao likeDao;

    public int postLikeIncrease(Long post_id) {
        
        int result = likeDao.postLikeIncrease(post_id);
        
        return result;
    }
    
    public int postLikeDecrease(Long post_id) {

        int result = likeDao.postLikeDecrease(post_id);
        
        return result;
    }

    public PostLike likeCount(Long post_id) {
        
        PostLike postLike = likeDao.likeCount(post_id);
        
        return postLike;
    }

    // 좋아요 싫어요의 상태를 확인
    public int postLikeCheck(Long post_id, String user_id) {
        
        int likeCheck = likeDao.postLikeCheck(post_id, user_id);
        log.debug("checkResult = {}", likeCheck);
        
        return likeCheck;
    }

    public int makePostLikeCheck(PostLikeDto dto) {
        
        int result = likeDao.makePostLikeCheck(dto);
        
        return result;
    }

    public PostLikeCheck postLikeDetailCheck(Long post_id, String user_id) {
        
        PostLikeCheck postLike = likeDao.postLikeDetailCheck(post_id, user_id);
        
        return postLike;
    }

    public void likeCheck(PostLikeDto dto) {
        
        likeDao.likeCheck(dto);
        
    }

    public void dislikeCheck(PostLikeDto dto) {
        
        likeDao.dislikeCheck(dto);
        
    }

    public int postLikeCancel(Long post_id) {
        
        int result = likeDao.postLikeCancel(post_id);
        
        return result;
    }

    public void likeCheckCancel(PostLikeDto dto) {
        
        likeDao.likeCheckCancel(dto);
        
    }

    public int postDislikeCancel(Long post_id) {
        
        int result = likeDao.postDislikeCancel(post_id);
        
        return result;
    }

    public void dislikeCheckCancel(PostLikeDto dto) {
        
        likeDao.dislikeCheckCancel(dto);
        
    }

}
