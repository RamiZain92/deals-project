package com.cybersolution.fazeal.logistics.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.common.logistics.dto.ReviewRequestDTO;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.Status;
import com.cybersolution.fazeal.logistics.entity.ReviewsEntity;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.repository.ReviewsRepository;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import com.cybersolution.fazeal.logistics.service.ReviewService;
import com.cybersolution.fazeal.logistics.service.mapper.ReviewsMapper;
import com.cybersolution.fazeal.logistics.util.Messages;

@Service	
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewsRepository reviewsRepository; 
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReviewsMapper reviewsMapper;

	@Override
	public MessageResponse addReview(ReviewRequestDTO reviewRequestDTO) {
		reviewValidation(reviewRequestDTO);
		UserEntity user = userRepository.findByIdAndActive(reviewRequestDTO.getDriverId(),Status.ACTIVE).orElseThrow(()->
				new GenericException(HttpStatus.NOT_FOUND, AppConstants.USER_NOT_FOUND,
						messages.get(AppConstants.USER_NOT_FOUND)));
		ReviewsEntity reviewsEntity = reviewsMapper.requestToEntity(reviewRequestDTO, user);
		reviewsEntity.setCreatedDate(LocalDateTime.now());
		reviewsRepository.save(reviewsEntity);
		return MessageResponse.builder().message(messages.get(AppConstants.REVIEW_ADDED_SUCCESSFULLY)).build();
	}

	public void reviewValidation(ReviewRequestDTO reviewRequestDTO) {
		if(Objects.isNull(reviewRequestDTO.getFeedback()) || reviewRequestDTO.getFeedback().isBlank()) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.REVIEW_FEEDBACK_NOT_BLANK,
					messages.get(AppConstants.REVIEW_FEEDBACK_NOT_BLANK));
		}
		if(reviewRequestDTO.getRating() < 0 ||  reviewRequestDTO.getRating() > 5) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.REVIEW_RATING_VALIDATION,
					messages.get(AppConstants.REVIEW_RATING_VALIDATION));
		}
	}

	@Override
	public MessageResponse updateReview(Long id, ReviewRequestDTO reviewRequestDTO) {
		ReviewsEntity reviewsEntity = reviewsRepository.findByIdAndCustomerId(id,reviewRequestDTO.getCustomerId()).orElseThrow(()->
			new GenericException(HttpStatus.NOT_FOUND, AppConstants.REVIEW_NOT_FOUND,
				messages.get(AppConstants.REVIEW_NOT_FOUND)));
		reviewValidation(reviewRequestDTO);
		reviewsEntity.setFeedback(reviewRequestDTO.getFeedback());
		reviewsEntity.setSuggestions(reviewRequestDTO.getSuggestion());
		reviewsEntity.setRating(reviewRequestDTO.getRating());
		reviewsEntity.setUpdatedDate(LocalDateTime.now());
		reviewsRepository.save(reviewsEntity);
		return MessageResponse.builder().message(messages.get(AppConstants.REVIEW_UPDATED_SUCCESSFULLY)).build();
	}
}
