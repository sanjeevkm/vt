package com.skm.vt.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PortMonthlyTrafficDTO {
	// Number of total arrivals for that port
	@Getter
	@Setter
	private Integer totalArrivedVesselCount;
	// Number of unique vessels for arrivals
	@Getter
	@Setter
	private Integer uniqueArrivedVesselCount;
	// Avg duration of port visit
	@Getter
	@Setter
	private Long averageVesselDockingTime;
	@Getter
	@Setter
	// Sum of length of the ships of all port visits (how many meters arrived at
	// this month)
	private Double totalArrivedVesselLength;
}