package com.skm.vt.service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.skm.vt.dao.DataLoaderDAO;
import com.skm.vt.model.Port;
import com.skm.vt.model.PortVessel;
import com.skm.vt.model.Vessel;

@Service
public class CSVDataLoaderServiceImpl implements DataLoaderService {

	@Autowired
	DataLoaderDAO csvDataLoaderDAO;

	@Transactional
	@Override
	public void save(Path path) throws IOException {
		String[] line;
		Port port = null;
		Vessel vessel = null;
		PortVessel portVessel = null;
		Map<String, Port> portMap = new HashMap<>();
		Map<String, Vessel> vesselMap = new HashMap<>();
		Set<Object> entitySet = new HashSet<>();
		Set<PortVessel> portVesselSet = null;
		try (CSVReader reader = new CSVReader(new FileReader(path.toString()))) {
			// Ignore first line in CSV file containing column names
			line = reader.readNext();

			while ((line = reader.readNext()) != null) {

				if (vesselMap.containsKey(line[1])) {
					vessel = vesselMap.get(line[1]);
				} else {
					vessel = getVesselEntity(line);
					vesselMap.put(line[1], vessel);
				}

				if (portMap.containsKey(line[3])) {
					port = portMap.get(line[3]);
				} else {
					port = getPortEntity(line);
					portMap.put(line[3], port);
				}

				portVessel = getPortVesselEntity(line);
				portVessel.setPort(port);
				portVessel.setVessel(vessel);

				portVesselSet = vessel.getPortVessel();
				if (portVesselSet != null) {
					portVesselSet.add(portVessel);
				} else {
					portVesselSet = new HashSet<>();
					portVesselSet.add(portVessel);
				}
				vessel.setPortVessel(portVesselSet);

				portVesselSet = port.getPortVessel();
				if (portVesselSet != null) {
					portVesselSet.add(portVessel);
				} else {
					portVesselSet = new HashSet<>();
					portVesselSet.add(portVessel);
				}
				port.setPortVessel(portVesselSet);

				entitySet.add(port);
				entitySet.add(vessel);
				entitySet.add(portVessel);
			}

			csvDataLoaderDAO.save(entitySet);
		}
	}

	private Vessel getVesselEntity(String[] line) {
		Vessel entity = new Vessel();
		entity.setName(line[0]);
		entity.setImo(Integer.valueOf(line[1]));
		entity.setLength(Double.valueOf(line[2]));

		return entity;
	}

	private Port getPortEntity(String[] line) {
		Port port = new Port();
		port.setPortId(Integer.valueOf(line[3]));

		return port;
	}

	private PortVessel getPortVesselEntity(String[] line) {
		PortVessel portVessel = new PortVessel();
		portVessel.setTimeStarted(Timestamp.valueOf(line[4]));
		portVessel.setTimeFinished(Timestamp.valueOf(line[5]));

		return portVessel;
	}
}
