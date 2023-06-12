package com.cybersolution.fazeal.logistics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper=false,onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Entity
@Table(name = "countries")
public class CountryEntity implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "shortname")
    private String shortName;

	@Column(name="name",nullable = false, length = 45)
	private String name;
	
	@Column(name="code",nullable = false, length = 5)
	private String code;
	
	@OneToMany(mappedBy = "countryEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<StateEntity> stateEntities = new ArrayList<>();
	

	@Override
	public String toString() {
		return Long.toString(id);
	}
}
