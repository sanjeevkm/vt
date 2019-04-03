package com.skm.vt.service;

import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skm.vt.dao.PortVesselDAO;
import com.skm.vt.dto.IMODockingTimeDTO;
import com.skm.vt.dto.PortMonthlyTrafficDTO;
import com.skm.vt.dto.PortTimePeriodTrafficDTO;
import com.skm.vt.dto.VesselDetailDTO;

//Service for port endpoints
@Service
public class PortService {
	@Autowired
	private PortVesselDAO portVesselDAO;

	public PortTimePeriodTrafficDTO getPortTimePeriodTraffic(Integer portId, Timestamp startTime, Timestamp endTime) {

		List<Object[]> vesselDataList = portVesselDAO.getPortTimeRangeTrafficSummary(portId, startTime, endTime);

		PortTimePeriodTrafficDTO dto = getPortTimePeriodTrafficDTO(vesselDataList);

		return dto;
	}

	public VesselDetailDTO[] getPortTraffic(Integer portId, Timestamp time) {

		List<Object[]> vesselTimeList = portVesselDAO.getPortTraffic(portId, time);

		VesselDetailDTO[] dto = getVesselDetailDTO(vesselTimeList);

		return dto;
	}

	public PortMonthlyTrafficDTO getPortMonthlyTraffic(Integer portId, Integer year, Integer month) {
		Timestamp startTime = Timestamp.valueOf(new StringBuilder(year + "-" + month + "-01 00:00:00.000").toString());

		// Find days in a month, required to set month end date
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		Timestamp endTime = Timestamp
				.valueOf(new StringBuilder(year + "-" + month + "-" + daysInMonth + " 12:00:00.000").toString());

		List<Object[]> vesselDataList = portVesselDAO.getPortTimeRangeTrafficSummary(portId, startTime, endTime);

		PortMonthlyTrafficDTO dto = getPortMonthlyTrafficDTO(vesselDataList);

		return dto;
	}

	private PortTimePeriodTrafficDTO getPortTimePeriodTrafficDTO(List<Object[]> vesselDataList) {
		PortTimePeriodTrafficDTO dto = new PortTimePeriodTrafficDTO();

		if (vesselDataList == null || vesselDataList.isEmpty())
			return dto;

		Long dockingTime = 0l;
		Long totalDockingTime = 0l;
		Long maximumDockingTime = Long.MIN_VALUE;
		Long minimumDockingTime = Long.MAX_VALUE;
		Integer minimumDockingVesselIMO = Integer.MIN_VALUE;
		Integer maximumDockingVesselIMO = Integer.MIN_VALUE;

		Set<Integer> uniqueVesselSet = new HashSet<>();

		for (Object[] pv : vesselDataList) {

			uniqueVesselSet.add((Integer) pv[2]);

			dockingTime = ((Timestamp) pv[1]).getTime() - ((Timestamp) pv[0]).getTime();

			if (minimumDockingTime > dockingTime) {
				minimumDockingTime = dockingTime;
				minimumDockingVesselIMO = (Integer) pv[2];
			}

			if (maximumDockingTime < dockingTime) {
				maximumDockingTime = dockingTime;
				maximumDockingVesselIMO = (Integer) pv[2];
			}

			totalDockingTime += dockingTime;
		}

		dto.setMaximumDockingTime(new IMODockingTimeDTO(maximumDockingVesselIMO, maximumDockingTime));
		dto.setMinimumDockingTime(new IMODockingTimeDTO(minimumDockingVesselIMO, minimumDockingTime));
		dto.setUniqueVessels(uniqueVesselSet);
		dto.setAverageDockingTime(totalDockingTime / vesselDataList.size());

		return dto;
	}

	private VesselDetailDTO[] getVesselDetailDTO(List<Object[]> vesselTimeList) {
		VesselDetailDTO[] vesselDetailDTOArray = vesselTimeList.stream().map((Object[] o) -> {
			VesselDetailDTO dto = new VesselDetailDTO();
			dto.setName((String) o[0]);
			dto.setImo((Integer) o[1]);
			dto.setLength((Double) o[2]);
			dto.setTimeStarted((Timestamp) o[3]);
			return dto;
		}).toArray(VesselDetailDTO[]::new);

		return vesselDetailDTOArray;
	}

	private PortMonthlyTrafficDTO getPortMonthlyTrafficDTO(List<Object[]> vesselDataList) {
		PortMonthlyTrafficDTO dto = new PortMonthlyTrafficDTO();

		if (vesselDataList == null || vesselDataList.isEmpty())
			return dto;

		Long dockingTime = 0l;
		Long totalDockingTime = 0l;
		Double totalArrivedVesselLength = 0d;
		Set<Integer> uniqueVesselSet = new HashSet<>();

		for (Object[] pv : vesselDataList) {
			uniqueVesselSet.add((Integer) pv[2]);

			dockingTime = ((Timestamp) pv[1]).getTime() - ((Timestamp) pv[0]).getTime();
			totalDockingTime += dockingTime;

			totalArrivedVesselLength += (Double) pv[3];
		}

		dto.setTotalArrivedVesselCount(vesselDataList.size());
		dto.setUniqueArrivedVesselCount(uniqueVesselSet.size());
		dto.setAverageVesselDockingTime(totalDockingTime / vesselDataList.size());
		// TODO: may need to truncate double number
		dto.setTotalArrivedVesselLength(totalArrivedVesselLength);

		return dto;
	}
}