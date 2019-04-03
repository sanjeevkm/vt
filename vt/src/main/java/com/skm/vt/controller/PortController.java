package com.skm.vt.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skm.vt.dto.PortMonthlyTrafficDTO;
import com.skm.vt.dto.PortTimePeriodTrafficDTO;
import com.skm.vt.dto.VesselDetailDTO;
import com.skm.vt.exception.APIException;
import com.skm.vt.service.PortService;

//Controller to  handle Port related endpoints.
@RestController
@RequestMapping(value = "/port")
public class PortController {
	@Autowired
	private PortService portService;

	// API - A) List vessels that have been in a port at a given time.
	@GetMapping(path = "/{portId}/time/{time}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<VesselDetailDTO[]> getPortTraffic(
			@PathVariable(value = "portId", required = true) Integer portId,
			@PathVariable(value = "time", required = true) Timestamp time) throws APIException {

		// API input validation is skipped as input conversion will take care of input
		// format.
		VesselDetailDTO[] dto = portService.getPortTraffic(portId, time);

		return new ResponseEntity<VesselDetailDTO[]>(dto, HttpStatus.OK);
	}

	// API - B) Time-period summary / aggregation
	@GetMapping(path = "/{portId}/start/{startTime}/end/{endTime}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<PortTimePeriodTrafficDTO> getPortTimeRangeTraffic(
			@PathVariable(value = "portId", required = true) Integer portId,
			@PathVariable(value = "startTime", required = true) Timestamp startTime,
			@PathVariable(value = "endTime", required = true) Timestamp endTime) throws APIException {

		// API input validation is skipped as input conversion will take care of input
		// format.
		PortTimePeriodTrafficDTO dto = portService.getPortTimePeriodTraffic(portId, startTime, endTime);

		return new ResponseEntity<PortTimePeriodTrafficDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(path = "/{portId}/year/{year}/month/{month}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<PortMonthlyTrafficDTO> getPortMonthlyTraffic(@PathVariable(value = "portId", required = true) Integer portId,
			@PathVariable(value = "year", required = true) Integer year,
			@PathVariable(value = "month", required = true) Integer month) throws APIException {

		// API input validation is skipped as input conversion will take care of input
		// format.
		PortMonthlyTrafficDTO dto = portService.getPortMonthlyTraffic(portId, year, month);

		return new ResponseEntity<PortMonthlyTrafficDTO>(dto, HttpStatus.OK);
	}
}
