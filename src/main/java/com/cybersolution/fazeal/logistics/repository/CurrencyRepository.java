package com.cybersolution.fazeal.logistics.repository;

import java.util.List;
import java.util.Optional;

import com.cybersolution.fazeal.logistics.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long>{

	@Query(value = "Select c from CurrencyEntity c")
	List<CurrencyEntity> getAllCurrencies();
	
	Optional<CurrencyEntity> findByCode(String code);
}
