package com.itwill.project.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.project.domain.BestTopic;
import com.itwill.project.domain.MemberPostListItem;
import com.itwill.project.domain.TopWriter;
import com.itwill.project.dto.member.MemberPostPageDto;
import com.itwill.project.dto.setting.SettingPageDto;
import com.itwill.project.service.BestTopicService;
import com.itwill.project.service.MemberService;
import com.itwill.project.service.SettingService;
import com.itwill.project.service.WriterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberPostListController {
	private final MemberService memberService;
	
	private final WriterService writerService;
	
	private final BestTopicService bestTopicService;
	//닉네임을 가져와서 그 유저가 쓴 게시글들을 보여준다(페이지 기능)
	@GetMapping("/memberPostList")
	public void showMemberPostList(Model model,@RequestParam(defaultValue = "1") int currentPage,@RequestParam String nickname) {
		
		//1.전체 게시물 개수를 가져온다(페이지 수 계산)
		int total=memberService.selectTotalPages(nickname);
		
		//회원의 프로필 이미지
		String profile_url = memberService.selectMemberProfileUrl(nickname);
		
		//모델에 등록
		
		model.addAttribute("profile_url", profile_url);
		
		model.addAttribute("nickname", nickname);
		
		// 게시물이 1개 이상이면
		if(total != 0) {
			//한 페이지에 보여줄 게시물 수(일단 10개로,,)
			 int size = 10;
			 
			 //전체 페이지 수 계산..
			 int totalPages = (int) (Math.ceil((total * 1.0)/size));
			 
			 log.debug("@@@@@@@@@@ 총 페이지 수={}",totalPages);
			 //페이지 기능을 위해서 총 페이지 수,현재 페이지를 보냄 
			 model.addAttribute("pagesCount", totalPages);
			 model.addAttribute("currentPage", currentPage);
			 
			 
			//가져올 테이블 데이터의 시작 번호 
			 int startNum = (currentPage - 1) * size +1;
			log.debug("게시물 시작 번호 : {}", startNum);
					
			//가져올 테이블 데이터의 시작 번호 
			int endNum = (currentPage -1) * size +size;
			log.debug("게시물 끝 번호 : {}", endNum);
			
			MemberPostPageDto dto = MemberPostPageDto.builder().nickname(nickname).startNum(startNum).endNum(endNum).build();
			
			//3.페이지에 따른 회원의 post 정보들을 가져오기..
			
			List<MemberPostListItem> list = memberService.selectMemberPostList(dto);
			
			//4.게시물들에 있는 해시태그값들 가져오기(for문을 통해서 있는 리스트에만 해시태그값을 넣어준다..
			for(MemberPostListItem post : list) {
				int post_id_int = post.getPost_id();
				
				Long post_id =  Long.valueOf(post_id_int);
				
				List<String> hashtags = memberService.selectPostHashtag(post_id);
				
				if(hashtags != null) {
					post.setHashTag(hashtags);
					log.debug("@@@@@@@@@@@@해시태그={}",hashtags);
				}
			}
			
			model.addAttribute("post", list);
			
			
			
		}
		//전체 게시물 수 
		model.addAttribute("postCount", total);
		
		 List<TopWriter> writerList = writerService.readTopWriter();
	        log.debug("Top Writer list: {}", writerList);
	        model.addAttribute("writer", writerList);
	        
	        //모든 포스트 목록 가져오기
	        List<BestTopic> bestAllTopicList = bestTopicService.readAllBestTopic(10);
	        log.debug("Best All Topic list : {} " ,bestAllTopicList);        
	        model.addAttribute("allTopic", bestAllTopicList);
		
	}
}
