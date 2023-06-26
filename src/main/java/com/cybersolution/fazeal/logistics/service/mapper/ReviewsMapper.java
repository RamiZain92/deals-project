package com.cybersolution.fazeal.logistics.service.mapper;

import org.springframework.stereotype.Component;

import com.cybersolution.fazeal.common.logistics.dto.ReviewRequestDTO;
import com.cybersolution.fazeal.logistics.entity.ReviewsEntity;
import com.cybersolution.fazeal.logistics.entity.UserEntity;

@Component
public class ReviewsMapper {

	public ReviewsEntity requestToEntity(ReviewRequestDTO reviewRequestDTO,UserEntity user) {
		return ReviewsEntity.builder().customerId(reviewRequestDTO.getCustomerId()).feedback(reviewRequestDTO.getFeedback())
				.suggestions(reviewRequestDTO.getSuggestion()).userEntity(user).rating(reviewRequestDTO.getRating()).build();
	}
}
