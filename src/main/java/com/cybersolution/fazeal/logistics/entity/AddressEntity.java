package com.cybersolution.fazeal.logistics.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "business_address")
public class AddressEntity extends AbstractBasedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "location_name", nullable = true, columnDefinition = "TEXT")
	private String locationName;

	@Column(name = "description", nullable = true, columnDefinition = "TEXT")
	private String description;

	@Column(name = "address_line_one", nullable = false, columnDefinition = "TEXT")
	private String addressLineOne;

	@Column(name = "address_line_two", nullable = true, columnDefinition = "TEXT")
	private String addressLineTwo;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private CountryEntity country;

	@Column(name = "state", length = 30, nullable = false)
	private String state;

	@Column(name = "city", length = 30, nullable = false)
	private String city;

	@Column(name = "zip_code", nullable = true, length = 30)
	private String zipCode;

	@Column(name = "street_number", nullable = true, length = 30)
	private String streetNumber;

	@Column(name = "po_box", nullable = true, length = 30)
	private String poBox;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

}
