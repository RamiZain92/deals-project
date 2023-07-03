package com.cybersolution.fazeal.logistics.graphql.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.cybersolution.fazeal.common.logistics.dto.DriverReviewResponse;
import com.cybersolution.fazeal.logistics.service.ReviewService;

@Service
@Validated
public class ReviewsQuery implements GraphQLQueryResolver {
	
	@Autowired
	private ReviewService reviewService;
	
	public List<DriverReviewResponse> getAllReviews(Integer pageNum, Integer pageSize,
			String keyword){
		return reviewService.getAllReviews(pageNum, pageSize, keyword);
	}

	public List<DriverReviewResponse> getAllReviewsOfDriver(Long id){
		return reviewService.getReviewsOfDriver(id);
	}
	
	public double getAvgRatingOfDriver(Long id) {
		return reviewService.getAvgRatingOfDriver(id);
	}
}
