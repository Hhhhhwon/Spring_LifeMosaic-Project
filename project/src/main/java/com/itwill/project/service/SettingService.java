package com.itwill.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.project.domain.MyBookmarkListItemByPaging;
import com.itwill.project.domain.MyCommentListItem;
import com.itwill.project.domain.MyCommentListItemByPaging;
import com.itwill.project.domain.SettingUser;
import com.itwill.project.dto.setting.SettingNicknameDto;
import com.itwill.project.dto.setting.SettingPageDto;
import com.itwill.project.dto.setting.SettingProfileImgDto;
import com.itwill.project.repository.PostDao;
import com.itwill.project.repository.SettingDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class SettingService {
	private final SettingDao settingDao;
	private final PostDao postDao;
	
	public SettingUser read(String user_id) {
		SettingUser user = settingDao.selectBySettingUser(user_id);
		log.debug("SettingService : user={}",user);
		return user;
	}
	
	public boolean checkNickname(String nickname) {
		log.debug("checkNickname(nickname={})",nickname);
		
		SettingUser user = settingDao.selectNickname(nickname);
		
		log.debug("User={}",user);
		
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void updateImg(String user_id,String profile_url) {
		log.debug("updateImg=(user_id={}, profile_url={})",user_id, profile_url );
		
		int result =settingDao.updateProfileUrl(SettingProfileImgDto.builder().user_id(user_id).profile_url(profile_url).build());
		
		log.debug("SettingService(updateImg result={}",result);
	}
	
	public int updateNickname(SettingNicknameDto dto) {
		log.debug("@@@@@@@@@@@@@   updateNickname={}",dto);
		
		int result = settingDao.updateNickname(dto);
		
		log.debug("@@@@@@@@@@@@@2SettingService(updateNickname result={}",result);
		
		return result;
	}
	
	public void updateBasicImg(String user_id) {
		int result = settingDao.updateBasicProfileImg(user_id);
		
	}
	public List<MyCommentListItem> selectMyComment(String user_id){
		return settingDao.selectMyComment(user_id);
	}
	
	public List<MyCommentListItemByPaging> selectMyCommentByPaging(SettingPageDto dto){
		
		log.debug("@@@@@@@@@@@@@@@2페이징 시작");
		return settingDao.selectByMyCommentByPaging(dto);
		
	}
	public int selectBookmarkTotalPages(String user_id) {
		int result = settingDao.selectBookmarkTotalPages(user_id);
		log.debug("@@@@@@@@@@@@@@@@@@@@ 전체 북마크 수={}",result);
		
		return result;
	}
	
	public List<MyBookmarkListItemByPaging> selectBookmarkByPaging(SettingPageDto dto){
		
		log.debug("@@@@@@@@  북마크 페이징 시작..");
		
		return settingDao.selectBookmarkByPaging(dto);
	}
	
	//해시 태그 가져오기
	//해시태그들 가져오기
	
		public List<String> selectPostHashtag(Long post_id){
			
			log.debug("해시태그들 가져오기");
			
			return postDao.selectHashtagByPostid(post_id);
		}


}
