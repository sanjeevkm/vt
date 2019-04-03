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

import com.skm.vt.dto.VesselTimePeriodActivityDTO;
import com.skm.vt.exception.APIException;
import com.skm.vt.service.VesselService;

//Controller to  handle Port related endpoints.
@RestController
@RequestMapping(value = "/vessel")
public class VesselController {

	@Autowired
	private VesselService vesselService;

	// API - C) Summary by vessel
	@GetMapping(path = "/{imoNo}/port/{portId}/start/{startTime}/end/{endTime}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<VesselTimePeriodActivityDTO> getVesselSummary(
			@PathVariable(value = "imoNo", required = true) Integer imoNo,
			@PathVariable(value = "portId", required = true) Integer portId,
			@PathVariable(value = "startTime", required = true) Timestamp startTime,
			@PathVariable(value = "endTime", required = true) Timestamp endTime) throws APIException {

		// API input validation is skipped as input conversion will take care of input
		// format.
		VesselTimePeriodActivityDTO dto = vesselService.getVesselSummary(imoNo, portId, startTime, endTime);

		return new ResponseEntity<VesselTimePeriodActivityDTO>(dto, HttpStatus.OK);
	}
}
