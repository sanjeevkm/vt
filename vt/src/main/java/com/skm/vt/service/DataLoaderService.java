package com.skm.vt.service;

import java.io.IOException;
import java.nio.file.Path;

public interface DataLoaderService {

	public void save(Path path) throws IOException;
}
