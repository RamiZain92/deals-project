package com.cybersolution.fazeal.logistics.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionDTO {
	
	private Long id;
	private Long userId;
	private Long businessId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> roles;
}
