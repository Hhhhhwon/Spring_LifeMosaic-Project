package com.itwill.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.stereotype.Service;

import com.itwill.project.domain.HashTag;
import com.itwill.project.domain.Post;
import com.itwill.project.domain.PostDetail;
import com.itwill.project.dto.post.PostCreateDto;
import com.itwill.project.dto.post.PostListItemDto;
import com.itwill.project.dto.post.PostModifyDto;
import com.itwill.project.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostDao postDao;


    public List<PostListItemDto> read(Long pageEnd, Long pageStart) {
        
        List<Post> list = postDao.selectOrderByDesc(pageEnd, pageStart);
        log.debug("전체포스트 목록 개수 = {}", list.size());
        
        return list.stream().map(PostListItemDto::fromEntity).toList();
    }
    
    public List<PostListItemDto> readBySubCategoryId(Long subCategoryId, Long pageEnd, Long pageStart) {
        
        List<Post> list = postDao.selectBySubCategoryIdOrderByDesc(subCategoryId, pageEnd, pageStart);
        log.debug("전체포스트 목록 개수 = {}", list.size());
        
        return list.stream().map(PostListItemDto::fromEntity).toList();
    }
    
    public Long getTotal() {
        
        return postDao.postCount();
    }
    
    public Long getTotal(Long subCategoryId) {
        
        return postDao.postCountBySubCategoryId(subCategoryId);
    }


    public int create(PostCreateDto dto) {
    	log.debug("create 호출");
    	
    	//연수 코드 수정 - 생성된 post_id 사용하기 위해
        Post post = dto.toEntity();
        post.setPost_id(0L);
        
        int result = postDao.insert(post);
        
        //해시태그가 존재하는지 체크
        if(post.getHashTag() != null) {
        	log.debug("null이 아님 : {}", post.getHashTag().size());
        	
            //생성된 post_id 가져오기
        	Long newPostId = post.getPost_id();
        	
        	//HashTag 모델 설정 - 포스트 아이디, 태그이름들
        	HashTag tag = HashTag.builder().post_id(newPostId).hashTag(post.getHashTag()).build();
        	
        	//HashTag를 파라미터로 postHashtag 테이블에 insert하기
        	int cnt = postDao.insertsPostHashTag(tag);
        }

        return result;
    }

    
    public PostDetail detail(Long post_id) {
        log.debug("post_id = {}", post_id);

        return postDao.selectById(post_id);
    }
    
    public List<String> readHash(Long post_id){
        return postDao.selectHashtagByPostid(post_id);
    }

    
    public int update(PostModifyDto dto) {
        
        int result = postDao.update(dto.ToPost());
        
        log.debug("update result = {}", result);
        
        
        //해시태그가 존재하는지 체크
        if(dto.getHashTag() != null) {
        	
        	log.debug("null이 아님 : {}", dto.getHashTag().size());
        	
        	//HashTag 모델 설정 - 포스트 아이디, 태그이름들
        	HashTag tag = HashTag.builder().post_id(dto.getPost_id()).hashTag(dto.getHashTag()).build();
        	
        	//HashTag를 파라미터로 postHashtag 테이블에 insert하기
        	postDao.insertsPostHashTag(tag);
        }
        
        
        return result;
    }

    public int delete(Long post_id) {

        int result = postDao.delete(post_id);
        log.debug("delete result = {}", result);
        
        return result;
    }

    public void viewCountIncrease(Long post_id) {
        
        int result = postDao.viewCountIncrease(post_id);
        log.debug("post_id = {}, viewCount + {}", post_id, result);
        
    }
    
    
    //기존테이블에 해시태그가 존재하는지 체크
    public void readHashtagName(List<String> tags) {
    	
    	log.debug("서비스에 들어온 태그들 : {} " , tags);
    	
    	for(String arr : tags) {
        	log.debug("태그 리스트: {} " , arr);
    		
    		String result = postDao.selectHashTag(arr);

    		if(result == null) {
    			log.debug("저장될 태그: {} " , arr);
    			postDao.insertHashTag(arr);
    		}
    	}
    }
    
    //포스트해시태그 테이블에 태그 삭제하기
    public int deletePostHash(Long post_id) {
        int result = postDao.deletePostHash(post_id);
        return result;
    }
    
    public List<String> readTagList(String value) {
        log.debug("서비스 실행 : {}", value);
        List<String> taglist = postDao.selectTagList(value);
        return taglist;
    }
}
