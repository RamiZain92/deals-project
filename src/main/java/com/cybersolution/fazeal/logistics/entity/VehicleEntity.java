package com.cybersolution.fazeal.logistics.entity;

import com.cybersolution.fazeal.logistics.constants.DrivingMethod;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "vehicles")
public class VehicleEntity extends AbstractBasedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type", length = 45, nullable = false)
	private String type;

	@Column(name = "license_plate", length = 45, nullable = false)
	private String licensePlate;

	@Column(name = "brand", length = 45, nullable = false)
	private String brand;

	@Column(name = "model_name", length = 20, nullable = false)
	private String modelName;

	@Column(name = "capacity", length = 45, nullable = false)
	private String capacity;

	@Column(name = "fuel_type", length = 128, nullable = false)
	private String fuelType;

	@Column(name = "mileage", nullable = false, length = 64)
	private String mileage;

	@Column(name = "year", length = 45, nullable = false)
	private String year;

	@Column(name = "status", length = 45, nullable = false)
	private String status;

	@Column(name = "color", length = 45, nullable = false)
	private String color;

	@Enumerated(EnumType.STRING)
	@Column(name = "driving_method", length = 20, nullable = false)
	private DrivingMethod drivingMethod;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<VehicleImagesEntity> vehicleImagesEntities = new ArrayList<>();

}
