package com.skm.vt.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IMODockingTimeDTO {
	// Vessel IMO
	@Getter
	@Setter
	private Integer imo;
	// Vessel time - Max, min, avg
	@Getter
	@Setter
	private Long vesselDockingTime;
}
