package com.itwill.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.project.domain.MemberPostListItem;
import com.itwill.project.dto.member.MemberPostPageDto;
import com.itwill.project.repository.MemberDao;
import com.itwill.project.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberDao memberDao;
	private final PostDao postDao;
	
	//회원의 프로필 이미지 가져오기
	
	public String selectMemberProfileUrl(String nickname) {
		String result = memberDao.selectMemberProfileUrl(nickname);
		
		log.debug("@@@@@@회원 프로필 이미지={}",result);
		
		return result;
	}
	
	//페이징할 전체 게시글수 가져오기
	public int selectTotalPages(String nickname) {
		int result = memberDao.selectTotalPages(nickname);
		log.debug("@@@@@@@@@@@게시글 수={}",result);
		return result;
	}
	//게시물들 가져오기(페이지 기능)
	
	public List<MemberPostListItem> selectMemberPostList(MemberPostPageDto dto){
		
		return memberDao.selectMemberPostList(dto);
	}
	//해시태그들 가져오기
	
	public List<String> selectPostHashtag(Long post_id){
		
		log.debug("해시태그들 가져오기");
		
		return postDao.selectHashtagByPostid(post_id);
	}
}
