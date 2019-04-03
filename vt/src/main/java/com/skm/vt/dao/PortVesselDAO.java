package com.skm.vt.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PortVesselDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Object[]> getVesselSummary(Integer imoNo, Integer portId, Timestamp startTime, Timestamp endTime) {

		Query query = entityManager.createNativeQuery("select time_started, time_finished from port_vessel "
				+ "where imo=:imo and port_id=:port_id and time_started>=:time_started and time_finished<=:time_finished "
				+ "order by time_started");

		query.setParameter("imo", imoNo);
		query.setParameter("port_id", portId);
		query.setParameter("time_started", startTime.toString());
		query.setParameter("time_finished", endTime.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPortTimeRangeTrafficSummary(Integer portId, Timestamp startTime, Timestamp endTime) {
		Query query = entityManager.createNativeQuery(
				"select pv.time_started, pv.time_finished, pv.imo, v.length from port_vessel pv, vessel v "
						+ "where port_id=:port_id and time_started>=:time_started and time_finished<=:time_finished and pv.imo=v.imo");

		query.setParameter("port_id", portId);
		query.setParameter("time_started", startTime.toString());
		query.setParameter("time_finished", endTime.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPortTraffic(Integer portId, Timestamp time) {
		Query query = entityManager
				.createNativeQuery("select v.name, v.imo, v.length, pv.time_started from port_vessel pv, vessel v "
						+ "where pv.port_id=:port_id and pv.time_started<=:time_started "
						+ "and pv.time_finished>=:time_finished and pv.imo=v.imo");

		query.setParameter("port_id", portId);
		query.setParameter("time_started", time.toString());
		query.setParameter("time_finished", time.toString());

		return query.getResultList();
	}
}
