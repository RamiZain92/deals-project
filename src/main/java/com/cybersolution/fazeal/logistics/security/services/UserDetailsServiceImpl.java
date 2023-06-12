package com.cybersolution.fazeal.logistics.security.services;


import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybersolution.fazeal.logistics.constants.AppConstants;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUserName(username).orElse(null);
			
		return getAuth(user);

	}
	
	public UserDetailsImpl getAuth(UserEntity user) {

//		List<BusinessTypeWithRoleTypeAndBusinessDetailsMappingEntity> roleMappingForUserAndBusiness = businessTypeWithRoleTypeAndBusinessDetailsMappingRepository
//				.findByUserEntityAndCompanyId(user.getId(), companyId);

		GrantedAuthority authWithRole = new SimpleGrantedAuthority(user.getRoleEntity().getRoleType().name());

		return UserDetailsImpl.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).username(user.getUserName()).email(user.getEmail()).password(user.getPassword()).authorities(List.of(authWithRole)).build();

	}
	
}
