package com.itwill.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SearchOrderList {
	private String orderStatus;
	private Long subcategory;
	private String keyword;
	private String search_category;
}
