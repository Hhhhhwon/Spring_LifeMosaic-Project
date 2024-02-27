package com.itwill.project.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.project.domain.BestTopic;
import com.itwill.project.domain.TopWriter;
import com.itwill.project.service.BestTopicService;
import com.itwill.project.service.WriterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class TestController {

	private final WriterService writerService;
	private final BestTopicService bestTopicService;
    
    @GetMapping("/")
    public String home(Model model) {
    	
        // 최대 게시글 작성 회원 랭크
        List<TopWriter> writerList = writerService.readTopWriter();
        log.debug("Top Writer list: {}", writerList);
        model.addAttribute("writer", writerList);
        
        
        //모든 포스트 목록 가져오기
        List<BestTopic> bestAllTopicList = bestTopicService.readAllBestTopic(5);
        log.debug("Best All Topic list : {} " ,bestAllTopicList);        
        model.addAttribute("allTopic", bestAllTopicList);
        
        //"내 잘못이야?" 베스크 게시글 5개 선별
        List<BestTopic> falut = bestTopicService.readCategoryBestTopic(11L);
        model.addAttribute("faluts", falut);
        
        //"나 호구니?" 베스크 게시글 5개 선별
        List<BestTopic> idiot = bestTopicService.readCategoryBestTopic(21L);
        log.debug("***idiot list : {} " , idiot);
        model.addAttribute("idiots", idiot);
        
        //"부럽지?" 베스트 게시글 5개 선별
        List<BestTopic> envy = bestTopicService.readCategoryBestTopic(31L);
        model.addAttribute("envies", envy);
        
        
        List<BestTopic> carAccident = bestTopicService.readCategoryBestTopic(41L);
        model.addAttribute("cars",carAccident);
        
        List<BestTopic> gameAccident = bestTopicService.readCategoryBestTopic(42L);
        model.addAttribute("games",gameAccident);
        
        List<BestTopic> etcAccident = bestTopicService.readCategoryBestTopic(43L);
        model.addAttribute("etcs",etcAccident);

        
        
        return "Home"; // 뷰(jsp)의 경로(이름)를 리턴.
    }
}
