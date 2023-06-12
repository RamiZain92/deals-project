package com.cybersolution.fazeal.logistics.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_session_token")
public class UserSessionTokenEntity extends AbstractBasedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "jwt_access_token", nullable = false)
	private String jwtAccessToken;
	@Column(name = "refresh_token", nullable = false)
	private String refreshToken;

	@Column(name = "device_type")
	private String deviceType;
	@Column(name = "browser_type")
	private String browserType;
	@Column(name = "ip_address")
	private String ipAddress;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@Column(name ="expiry_date",nullable = false)
	private Instant expiryDate;

	@Override
	public String toString() {
		return Long.toString(id);
	}

}