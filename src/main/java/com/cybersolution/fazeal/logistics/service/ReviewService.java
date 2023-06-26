package com.cybersolution.fazeal.logistics.service;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.logistics.dto.ReviewRequestDTO;

public interface ReviewService {
	public MessageResponse addReview(ReviewRequestDTO reviewRequestDTO);
}
