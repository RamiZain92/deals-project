package com.cybersolution.fazeal.logistics.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.amazonaws.services.rekognition.model.GenderType;
import com.cybersolution.fazeal.logistics.constants.Agreements;
import com.cybersolution.fazeal.logistics.constants.Policies;
import com.cybersolution.fazeal.logistics.constants.SignUpProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "users")
public class UserEntity extends AbstractBasedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "middle_name", length = 45, nullable = true)
	private String middleName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 20, nullable = true)
	private GenderType genderType;

	@Column(name = "user_name", length = 45, nullable = false, unique = true)
	private String userName;

	@Column(name = "email", length = 128, nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false, length = 64)
	private String password;

	@Column(name = "contact_number", length = 45, nullable = false, unique = true)
	private String contactNumber;

	@Column(name = "alternative_email", length = 45, nullable = true)
	private String alternativeEmail;

	@Column(name = "alternative_contact_number", length = 45, nullable = true)
	private String alternativeContactNumber;

	@Column(name = "id_image", length = 500, nullable = false)
	private String userIdImageUrl;

	@Column(name = "licence_number", length = 500, nullable = false)
	private String userLicenceNumberImageUrl;

	@Column(name = "personal_image", length = 500, nullable = false)
	private String userPersonalImageUrl;

	@Column(name = "availability", nullable = false)
	private Boolean availability;

	@Enumerated(EnumType.STRING)
	@Column(name = "sign_up_process", length = 20)
	private SignUpProcess signUpProcess;

	@Enumerated(EnumType.STRING)
	@Column(name = "agreements", length = 10)
	private Agreements agreements;

	@Enumerated(EnumType.STRING)
	@Column(name = "policies", length = 10)
	private Policies policies;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@OneToOne
	@JoinColumn(name = "address_id")
	private AddressEntity addressEntity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<VehicleEntity> vehicleEntities = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roleEntity;

}
