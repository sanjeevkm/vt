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
@Table(name = "port")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Port implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column(name = "port_id", unique = true, updatable = false, nullable = false)
	private Integer portId;

	@Getter
	@Setter
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "port")
	private Set<PortVessel> portVessel;
}
