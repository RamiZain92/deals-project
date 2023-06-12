package com.cybersolution.fazeal.logistics.repository;

import com.cybersolution.fazeal.logistics.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

}
