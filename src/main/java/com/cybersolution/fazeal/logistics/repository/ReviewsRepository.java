package com.cybersolution.fazeal.logistics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cybersolution.fazeal.logistics.entity.ReviewsEntity;

@Repository
public interface ReviewsRepository extends PagingAndSortingRepository<ReviewsEntity, Long>{

	Optional<ReviewsEntity> findByIdAndCustomerId(Long id, Long customerId);
	
	@Query("SELECT r FROM ReviewsEntity r WHERE "
			+ " (r.feedback LIKE %?1% OR "
			+ " r.suggestions LIKE %?1%)")
	Page<ReviewsEntity> findAll(String keyword,Pageable pageable);
	
	@Query("SELECT r FROM ReviewsEntity r WHERE r.userEntity.id=?1")
	List<ReviewsEntity> findAllByUserId(Long id);
	
	@Query("SELECT AVG (r.rating) FROM ReviewsEntity r WHERE r.userEntity.id=?1")
	double getAvgOfDriverRating(Long id);
}
