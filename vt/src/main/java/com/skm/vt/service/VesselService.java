package com.skm.vt.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skm.vt.dao.PortVesselDAO;
import com.skm.vt.dto.VesselTimePeriodActivityDTO;

//Service for vessel endpoints
@Service
public class VesselService {

	@Autowired
	private PortVesselDAO portVesselDAO;

	public VesselTimePeriodActivityDTO getVesselSummary(Integer imoNo, Integer portId, Timestamp startTime,
			Timestamp endTime) {

		List<Object[]> portVesselList = portVesselDAO.getVesselSummary(imoNo, portId, startTime, endTime);

		VesselTimePeriodActivityDTO dto = getVesselTimeRangeSummaryDTO(portVesselList);

		return dto;
	}

	private VesselTimePeriodActivityDTO getVesselTimeRangeSummaryDTO(List<Object[]> portVesselList) {
		VesselTimePeriodActivityDTO dto = new VesselTimePeriodActivityDTO();

		if (portVesselList == null || portVesselList.isEmpty())
			return dto;

		Long dockingTime = 0l;
		Long averageDockingTime = 0l;
		Long minimumDockingTime = Long.MAX_VALUE;
		Long maximumDockingTime = Long.MIN_VALUE;

		for (Object[] pv : portVesselList) {
			dockingTime = ((Timestamp) pv[1]).getTime() - ((Timestamp) pv[0]).getTime();

			if (minimumDockingTime > dockingTime) {
				minimumDockingTime = dockingTime;
			}

			if (maximumDockingTime < dockingTime) {
				maximumDockingTime = dockingTime;
			}

			averageDockingTime += dockingTime;
		}

		dto.setAverageDockingTime(averageDockingTime / portVesselList.size());
		dto.setMaximumDockingTime(maximumDockingTime);
		dto.setMinimumDockingTime(minimumDockingTime);

		dto.setPortVisitCount(portVesselList.size());

		// Data is sorted (order by) time_started, hence first entry is earliest
		Object[] timeDataArray = portVesselList.get(0);
		dto.setEarliestPortVisitTime((Timestamp) timeDataArray[0]);

		// Data is sorted (order by) time_started, hence last entry is latest
		timeDataArray = portVesselList.get(portVesselList.size() - 1);
		dto.setLatestPortVisitTime((Timestamp) timeDataArray[0]);

		return dto;
	}
}
