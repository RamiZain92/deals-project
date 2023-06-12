package com.cybersolution.fazeal.logistics.repository;


import java.util.List;
import java.util.Optional;

import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import com.cybersolution.fazeal.logistics.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
	
	@Query("SELECT s FROM StateEntity s WHERE s.countryEntity.id = ?1")
	public List<StateEntity> findByCountryOrderByNameAsc(Long countryId);
	
	public Long countById(Long id);
	public Optional<StateEntity> findById(Long id);
	public Optional<StateEntity> findByName(String name);
	public Optional<StateEntity> findByNameAndCountryEntity(String name, CountryEntity countryEntity);
}
