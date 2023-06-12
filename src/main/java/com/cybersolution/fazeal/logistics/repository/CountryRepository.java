package com.cybersolution.fazeal.logistics.repository;


import java.util.List;
import java.util.Optional;

import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
	public List<CountryEntity> findAllByOrderByNameAsc();
	
	public Long countById(Long id);
	
	Optional<CountryEntity> findById(Long id);
	Optional<CountryEntity> findByName(String name);
}
