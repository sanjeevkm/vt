package com.skm.vt.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "VESSEL")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Vessel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column(name = "imo", unique = true, updatable = false, nullable = false)
	private Integer imo;

	@Getter
	@Setter
	@Column(name = "name", nullable = false)
	private String name;

	@Getter
	@Setter
	@Column(name = "length", nullable = false)
	private Double length;

	@Getter
	@Setter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "vessel")
	private Set<PortVessel> portVessel;
}
