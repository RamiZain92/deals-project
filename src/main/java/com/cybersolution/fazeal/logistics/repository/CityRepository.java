package com.cybersolution.fazeal.logistics.repository;

import java.util.List;
import java.util.Optional;

import com.cybersolution.fazeal.logistics.entity.CityEntity;
import com.cybersolution.fazeal.logistics.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository  extends JpaRepository<CityEntity, Long> {
	
	List<CityEntity> findByStateEntity(StateEntity stateEntity);
	
	Optional<CityEntity> findByNameAndStateEntity(String name,StateEntity stateEntity);

}
