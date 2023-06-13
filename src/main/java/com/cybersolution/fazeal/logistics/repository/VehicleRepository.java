package com.cybersolution.fazeal.logistics.repository;

import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.entity.VehicleEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    List<VehicleEntity> findByUserEntity(UserEntity userEntity);

    Optional<VehicleEntity> findByIdAndUserEntity(Long id, UserEntity userEntity);

}
