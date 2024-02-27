package com.itwill.project.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.project.domain.BestTopic;
import com.itwill.project.domain.Criteria;
import com.itwill.project.domain.PostDetail;
import com.itwill.project.domain.SearchOrderList;
import com.itwill.project.domain.TopWriter;
import com.itwill.project.dto.post.PageMakerDto;
import com.itwill.project.dto.post.PostCreateDto;
import com.itwill.project.dto.post.PostListItemDto;
import com.itwill.project.dto.post.PostModifyDto;
import com.itwill.project.service.BestTopicService;
import com.itwill.project.service.PostService;
import com.itwill.project.service.WriterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final WriterService writerService;
    private final BestTopicService bestTopicService;
    
    @GetMapping("/HallOfFame") 
    public void HallOfFame(Model model) {
        log.debug("HallOfFame()");
        
        List<TopWriter> writerList = writerService.readTopWriter();
        log.debug("Top Writer list: {}", writerList);
        model.addAttribute("writer", writerList);
        
    }
    
    @GetMapping("/totallist")
    public void totalList(Model model, Criteria cri, @RequestParam(value = "pageNo", defaultValue = "1") Long pageNum) {
        log.debug("totallist()");
        
        Long pageEnd = pageNum * 10;
        Long pageStart = ((pageNum-1)*10)+1;
        
        List<PostListItemDto> list = postService.read(pageEnd, pageStart);  
        
        model.addAttribute("posts", list);
        
        Long total = postService.getTotal();
        
        cri.setAmount(10);
        cri.setPageNum(pageNum);
        
        PageMakerDto pageMake = new PageMakerDto(cri, total);
        
        model.addAttribute("pageMaker", pageMake);
    }
    
    @GetMapping("/list/")
    public void listBySubCategoryId(Model model, Criteria cri, @RequestParam(name = "sub_category_id") Long sub_category_id, @RequestParam(value = "pageNo", defaultValue = "1") Long pageNum) {
        log.debug("listBySubCategoryId (sub_category_id = {})", sub_category_id);
        
        Long pageEnd = pageNum * 10;
        Long pageStart = ((pageNum-1)*10)+1;
        
        List<PostListItemDto> list = postService.readBySubCategoryId(sub_category_id, pageEnd, pageStart);  
        
        model.addAttribute("posts", list);
        
        Long total = postService.getTotal(sub_category_id);
        
        cri.setAmount(10);
        cri.setPageNum(pageNum);
        
        PageMakerDto pageMake = new PageMakerDto(cri, total);

        
        model.addAttribute("pageMaker", pageMake);
        model.addAttribute("cId", sub_category_id);
        
        List<TopWriter> writerList = writerService.readTopWriter();
        log.debug("Top Writer list: {}", writerList);
        model.addAttribute("writer", writerList);
        
        //모든 포스트 목록 가져오기
        List<BestTopic> bestAllTopicList = bestTopicService.readAllBestTopic(10);
        log.debug("Best All Topic list : {} " ,bestAllTopicList);        
        model.addAttribute("allTopic", bestAllTopicList);
        
        
    }
    
    @GetMapping("/create")
    public void create(Model model, @RequestParam(name = "sub_category_id") Long sub_category_id) {
        log.debug("create(sub_category_id = {})", sub_category_id);
        model.addAttribute("cId", sub_category_id);
    }
    
    @PostMapping("/create")
    public String create(@ModelAttribute PostCreateDto dto, @RequestParam(name = "sub_category_id") Long sub_category_id) {
        log.debug("게시글 작성이에용");

        // 1.1 수정 코드
        if(dto.getUser_id() != null && dto.getHashTag() != null) {
        	postService.readHashtagName(dto.getHashTag());        	
        }
        
    	//포스트 테이블에 포스트 저장
        postService.create(dto);
        
        return "redirect:/post/list/?sub_category_id=" + sub_category_id;
    }
    
    @GetMapping("/detail")
    public void detail(Model model, @RequestParam(name = "post_id") Long post_id) {
        log.debug("게시글 입니다.");
        
        postService.viewCountIncrease(post_id);
        
        PostDetail post = postService.detail(post_id);
        model.addAttribute("post", post);
        
        //연수 코드 추가 - 해시태그 가져오기
        List<String> list = postService.readHash(post_id);
        model.addAttribute("tags", list);
        
        List<TopWriter> writerList = writerService.readTopWriter();
        log.debug("Top Writer list: {}", writerList);
        model.addAttribute("writer", writerList);
        
        //모든 포스트 목록 가져오기
        List<BestTopic> bestAllTopicList = bestTopicService.readAllBestTopic(10);
        log.debug("Best All Topic list : {} " ,bestAllTopicList);        
        model.addAttribute("allTopic", bestAllTopicList);
        
    }
    
    @GetMapping("/modify")
    public void modify(Model model, @RequestParam(name = "post_id") Long post_id) {
        log.debug("게시글 입니다.");
        
        //연수 코드 추가 - 해시태그 가져오기
        List<String> list = postService.readHash(post_id);
        model.addAttribute("tags", list);

        
        PostDetail post = postService.detail(post_id);
        model.addAttribute("post", post);
        
    }
    
    @PostMapping("/update")
    public String modify(@ModelAttribute PostModifyDto dto) {
        log.debug("dto = {}", dto);
        
        //1.1 수정 코드
        //업데이트 하기 전에 해시태그 삭제
    	postService.deletePostHash(dto.getPost_id());

    	if(dto.getHashTag() != null) {
    	postService.readHashtagName(dto.getHashTag());
    	}
    	
        Long post_id = dto.getPost_id();
        
        postService.update(dto);
        
        return "redirect:/post/detail?post_id=" + post_id;
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "post_id") Long post_id, @RequestParam(name = "sub_category_id") Long sub_category_id) {
        log.debug("delete(post_id = {})", post_id);
        
        postService.deletePostHash(post_id);
        
        postService.delete(post_id);
        
        return "redirect:/post/list/?sub_category_id=" + sub_category_id;
    }
    
    
    @GetMapping("/searchlist")
    public void search(SearchOrderList dto, Model model) {
		if(dto.getOrderStatus() == null && dto.getSubcategory() == null) {
			dto.setOrderStatus("upto");
			dto.setSubcategory(0L);
		}
		
		model.addAttribute("searchKeyword", dto);
		
        List<TopWriter> writerList = writerService.readTopWriter();
        log.debug("Top Writer list: {}", writerList);
        model.addAttribute("writer", writerList);
        
        //모든 포스트 목록 가져오기
        List<BestTopic> bestAllTopicList = bestTopicService.readAllBestTopic(10);
        log.debug("Best All Topic list : {} " ,bestAllTopicList);        
        model.addAttribute("allTopic", bestAllTopicList);
	}

	@GetMapping("/readtaglist")
	@ResponseBody
	public ResponseEntity<List<String>> readAlltag(@RequestParam(name = "value") String value) {
		log.debug("컨트롤러 실행 : {}", value);
		List<String> list = postService.readTagList(value);

		return ResponseEntity.ok(list);
	}

}
