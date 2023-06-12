package com.cybersolution.fazeal.logistics.repository;

import com.cybersolution.fazeal.logistics.entity.UserOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRepository extends JpaRepository<UserOrdersEntity, Long> {

}
