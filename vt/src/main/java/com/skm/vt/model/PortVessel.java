package com.skm.vt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "port_vessel")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PortVessel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter
	@Setter
	@Column(name = "time_started")
	private java.sql.Timestamp timeStarted;

	@Getter
	@Setter
	@Column(name = "time_finished")
	private java.sql.Timestamp timeFinished;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "imo")
	private Vessel vessel;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "port_id")
	private Port port;
}
