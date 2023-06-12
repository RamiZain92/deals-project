package com.cybersolution.fazeal.logistics.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.cybersolution.fazeal.logistics.constants.Status;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractBasedEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "active")
	private Status active;

	@Column(name = "created_date", nullable = true)
	private LocalDateTime createdDate;

	@Column(name = "updated_date", nullable = true)
	private LocalDateTime updatedDate;

}
