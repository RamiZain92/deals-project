package com.cybersolution.fazeal.logistics.repository;

import java.util.Optional;

import com.cybersolution.fazeal.logistics.entity.RoleEntity;
import com.cybersolution.fazeal.logistics.constants.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByRoleType(RoleType roleType);

}
