package com.skm.vt.dto;

import java.sql.Timestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class VesselDetailDTO {
	// Name
	@Getter
	@Setter
	private String name;
	// IMO
	@Getter
	@Setter
	private Integer imo;
	// length
	@Getter
	@Setter
	private Double length;
	// time_started
	@Getter
	@Setter
	private Timestamp timeStarted;
}
