package com.cybersolution.fazeal.logistics.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cybersolution.fazeal.common.logistics.dto.DriverReviewResponse;
import com.cybersolution.fazeal.common.logistics.dto.ReviewRequestDTO;
import com.cybersolution.fazeal.logistics.entity.ReviewsEntity;
import com.cybersolution.fazeal.logistics.entity.UserEntity;

@Component
public class ReviewsMapper {

	public ReviewsEntity requestToEntity(ReviewRequestDTO reviewRequestDTO,UserEntity user) {
		return ReviewsEntity.builder().customerId(reviewRequestDTO.getCustomerId()).feedback(reviewRequestDTO.getFeedback())
				.suggestions(reviewRequestDTO.getSuggestion()).userEntity(user).rating(reviewRequestDTO.getRating()).build();
	}
	public DriverReviewResponse entityToResponse(ReviewsEntity reviewsEntity) {
		return DriverReviewResponse.builder().id(reviewsEntity.getId()).driverFirstName(reviewsEntity.getUserEntity().getFirstName())
				.driverLastName(reviewsEntity.getUserEntity().getLastName()).customerId(reviewsEntity.getCustomerId())
				.feedback(reviewsEntity.getFeedback()).rating(reviewsEntity.getRating()).suggestion(reviewsEntity.getSuggestions()).build();
	}
	
	public List<DriverReviewResponse> entitiesToResponse(List<ReviewsEntity> reviewEntities){
		return reviewEntities.stream().map(r -> entityToResponse(r)).collect(Collectors.toList());
	}
}
