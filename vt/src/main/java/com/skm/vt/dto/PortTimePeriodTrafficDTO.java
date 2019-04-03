package com.skm.vt.dto;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PortTimePeriodTrafficDTO {
	// Number of unique vessels (IMO number) during this time range
	@Getter
	@Setter
	private Set<Integer> uniqueVessels;
	// Average time in the port
	@Getter
	@Setter
	private Long averageDockingTime;
	// Minimum time in the port (include the IMO of the ship with minimum time)
	@Getter
	@Setter
	private IMODockingTimeDTO minimumDockingTime;
	// Maximum time in the port (include the IMO of the ship with the maximum time)
	@Getter
	@Setter
	private IMODockingTimeDTO maximumDockingTime;
}
