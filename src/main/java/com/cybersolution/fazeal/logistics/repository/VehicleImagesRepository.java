package com.cybersolution.fazeal.logistics.repository;

import com.cybersolution.fazeal.logistics.entity.VehicleImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImagesRepository extends JpaRepository<VehicleImagesEntity, Long> {

}
