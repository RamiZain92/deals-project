package com.cybersolution.fazeal.logistics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybersolution.fazeal.logistics.entity.ReviewsEntity;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long>{

	Optional<ReviewsEntity> findByIdAndCustomerId(Long id, Long customerId);
}
