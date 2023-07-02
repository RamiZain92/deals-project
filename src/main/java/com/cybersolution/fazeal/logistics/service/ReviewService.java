package com.cybersolution.fazeal.logistics.service;

import java.util.List;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.logistics.dto.DriverReviewResponse;
import com.cybersolution.fazeal.common.logistics.dto.ReviewRequestDTO;

public interface ReviewService {
	public MessageResponse addReview(ReviewRequestDTO reviewRequestDTO);
	
	public MessageResponse updateReview(Long id,ReviewRequestDTO reviewRequestDTO);
	
	public List<DriverReviewResponse> getAllReviews(Integer pageNum, Integer pageSize,String keyword);
}
