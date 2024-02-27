package com.itwill.project.repository;

import java.util.List;

import com.itwill.project.domain.TopWriter;

public interface WriterDao {
	List<TopWriter> selectTopWriter();
}
