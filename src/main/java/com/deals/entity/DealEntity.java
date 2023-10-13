package com.deals.entity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "deal")
public class DealEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "deal_unique_id",unique = true,nullable = false)
	private String dealUniqueId;

	@Column(name = "from_currency",nullable = false)
	private String fromCurrency;

	@Column(name = "to_currency",nullable = false)
	private String toCurrency;

	@Column(name = "deal_time",nullable = false)
	private LocalDateTime dealTime;

	@Column(name = "deal_amount",nullable = false)
	private double dealAmount;


	
}
