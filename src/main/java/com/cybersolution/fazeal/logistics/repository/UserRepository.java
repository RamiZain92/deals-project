package com.cybersolution.fazeal.logistics.repository;

import java.util.Optional;

import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.constants.SignUpProcess;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cybersolution.fazeal.logistics.constants.Status;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	Optional<UserEntity> findByIdAndActive(Long id, Status active);

	Optional<UserEntity> findByUserNameAndActive(String userName, Status active);

	Optional<UserEntity> findByIdAndSignUpProcess(Long id, SignUpProcess signUpProcess);

	@Override
	Optional<UserEntity> findById(Long id);

	Optional<UserEntity> findByUserName(String userName);

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByContactNumber(String contactNumber);

	Long countById(Long id);

}
