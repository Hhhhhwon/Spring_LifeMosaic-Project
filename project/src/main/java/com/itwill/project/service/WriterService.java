package com.itwill.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.project.domain.TopWriter;
import com.itwill.project.repository.WriterDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WriterService {
	
	private final WriterDao writerDao;
	
	public List<TopWriter> readTopWriter(){
		List<TopWriter> list = writerDao.selectTopWriter();
		return list;
	}
}
