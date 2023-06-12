package com.cybersolution.fazeal.logistics.entity;

import com.cybersolution.fazeal.common.order.constants.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "users_orders")
public class UserOrdersEntity extends AbstractBasedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_id", nullable = false)
	private Long orderId;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "pickup_address", length = 45, nullable = false)
	private String pickupAddress;

	@Column(name = "delivery_address", length = 20, nullable = false)
	private String deliveryAddress;

	@Column(name = "order_status", length = 45, nullable = false)
	private String orderStatus;

	@Column(name = "total_amount", nullable = false)
	private BigDecimal totalAmount;

	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", length = 45, nullable = false)
	private PaymentMethod paymentMethod;

	@Column(name = "payment_status", length = 45, nullable = false)
	private String paymentStatus;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<VehicleImagesEntity> vehicleImagesEntities = new ArrayList<>();

}
