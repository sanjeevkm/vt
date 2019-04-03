package com.skm.vt.dao;

import java.io.IOException;
import java.util.Set;

public interface DataLoaderDAO {
	
	public void save(Set<Object> data) throws IOException;

}
