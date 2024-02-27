package com.itwill.project.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.project.domain.SearchListItem;
import com.itwill.project.domain.SearchOrderList;
import com.itwill.project.service.SearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sort")
public class SearchRestController {
	
	private final SearchService searchService;
	
	@PostMapping("/category")
	public ResponseEntity<List<SearchListItem>> sortBySubCategory(@RequestBody SearchOrderList dto){
		log.debug("dto: {} " , dto);
		
		List<SearchListItem> list = searchService.selectSearchByKeyword(dto);
		
		log.debug("result list : {} ", list);
		
		return ResponseEntity.ok(list);
	}
}
