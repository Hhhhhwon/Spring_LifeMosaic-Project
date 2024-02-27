package com.itwill.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.SearchListItem;
import com.itwill.project.domain.SearchOrderList;

public interface SearchDao {
	List<SearchListItem> searchKeywordCategory(
			@Param("dto") SearchOrderList dto);
	
}
