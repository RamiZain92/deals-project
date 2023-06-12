package com.cybersolution.fazeal.logistics.repository;


import com.cybersolution.fazeal.logistics.entity.UserSessionTokenEntity;
import com.cybersolution.fazeal.logistics.constants.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionTokenRepository extends JpaRepository<UserSessionTokenEntity, Long> {
    Optional<UserSessionTokenEntity> findByRefreshToken(String refreshToken);
    Optional<UserSessionTokenEntity> findByIdAndActiveAndRefreshToken(Long id,Status active,String refreshToken);
    
    Optional<UserSessionTokenEntity> findByIdAndActive(Long id,Status active);
    
    @Modifying
	@Query("DELETE from UserSessionTokenEntity u where u.userEntity.id= ?1 AND u.refreshToken = ?2")
    void removeByUserIdAndRefreshToken(Long userId,String refreshToken);

}
