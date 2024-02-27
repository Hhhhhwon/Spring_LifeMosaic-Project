package com.itwill.project.repository;

import java.util.List;

import com.itwill.project.domain.BestTopic;

public interface BestTopicDao {
	List<BestTopic> selectAllBestTopic(int rank);
	
	List<BestTopic> selectCategoryBestTopic(Long id);
}
