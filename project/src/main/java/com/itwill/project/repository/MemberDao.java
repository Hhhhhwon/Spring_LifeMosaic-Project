package com.itwill.project.repository;

import java.util.List;

import com.itwill.project.domain.MemberPostListItem;
import com.itwill.project.dto.member.MemberPostPageDto;

public interface MemberDao {
	
	//프로필 이미지 가져오기
	String selectMemberProfileUrl(String nickname);
	
	//전체 게시물 가져오기
	int selectTotalPages(String nickname);
	
	//게시물 가져오기(페이지 기능)
	List<MemberPostListItem>  selectMemberPostList(MemberPostPageDto dto);
}
