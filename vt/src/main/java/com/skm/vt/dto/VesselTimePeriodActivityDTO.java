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
public class VesselTimePeriodActivityDTO {
	// Number of port visits of this vessel in that time range
	@Getter
	@Setter
	private Integer portVisitCount;
	// Average time in the port
	@Getter
	@Setter
	private Long averageDockingTime;
	// Minimum time in the port
	@Getter
	@Setter
	private Long minimumDockingTime;
	// Maximum tine in the port
	@Getter
	@Setter
	private Long maximumDockingTime;
	// Earliest visit (start time) in this time range
	@Getter
	@Setter
	private Timestamp earliestPortVisitTime;
	// Latest visit (start time) in this time range
	@Getter
	@Setter
	private Timestamp latestPortVisitTime;
}
