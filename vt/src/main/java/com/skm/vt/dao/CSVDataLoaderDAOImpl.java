package com.skm.vt.dao;

import java.io.IOException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CSVDataLoaderDAOImpl implements DataLoaderDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Set<Object> data) throws IOException {
		System.out.println("SKM CSVDataLoaderDAOImpl save data- "+data.size());

		data.forEach( i -> entityManager.persist(i));
	}
}
