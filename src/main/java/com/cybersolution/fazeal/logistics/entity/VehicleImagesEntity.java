package com.cybersolution.fazeal.logistics.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "vehicle_images")
public class VehicleImagesEntity extends AbstractBasedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_path", length = 255, nullable = false)
	private String imagePath;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private VehicleEntity vehicleEntity;

}
