package com.cybersolution.fazeal.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.logistics.dto.ReviewRequestDTO;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.service.ReviewService;

import io.swagger.annotations.Api;

@Api(value = AppConstants.REVIEW_CONTROLLER)
@CrossOrigin(origins = "*", maxAge = 4300)
@RequestMapping(value = "/api/v1")
@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/logistics/reviews/create")
	public ResponseEntity<MessageResponse> addReview(@RequestBody ReviewRequestDTO reviewRequestDTO){
		return ResponseEntity.ok(reviewService.addReview(reviewRequestDTO));
	}
}
