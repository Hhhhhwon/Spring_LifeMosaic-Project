
package com.itwill.project.repository;
import java.util.List;

import com.itwill.project.domain.MyBookmarkListItemByPaging;
import com.itwill.project.domain.MyCommentListItem;
import com.itwill.project.domain.MyCommentListItemByPaging;
import com.itwill.project.domain.SettingUser;
import com.itwill.project.dto.setting.SettingNicknameDto;
import com.itwill.project.dto.setting.SettingPageDto;
import com.itwill.project.dto.setting.SettingProfileImgDto;

public interface SettingDao {
	//프로필 정보 가져오기
	SettingUser selectBySettingUser(String user_id);
	//닉네임 정보 중복 확인
	SettingUser selectNickname(String nickname);
	
	//프로필 이미지 변경
	int  updateProfileUrl(SettingProfileImgDto dto);
	//닉네임 변경
	int updateNickname(SettingNicknameDto dto);
	//기본 프로필로 변경
	int updateBasicProfileImg(String user_id);
	//전체 댓글 가져오기
	List<MyCommentListItem> selectMyComment(String user_id);
	//페이지 기능을 위한 댓글
	List<MyCommentListItemByPaging> selectByMyCommentByPaging(SettingPageDto dto);
	
	//전체 북마크 수 가져오기
	int selectBookmarkTotalPages(String user_id);
	
	//페이지 기능을 위한 북마크
	List<MyBookmarkListItemByPaging> selectBookmarkByPaging(SettingPageDto dto);
	
}
