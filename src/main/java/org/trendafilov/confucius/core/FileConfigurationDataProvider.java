package org.trendafilov.confucius.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileConfigurationDataProvider implements ConfigurationDataProvider {
	String filename;

	public FileConfigurationDataProvider(String filename) {
		this.filename = filename;
	}

	@Override
	public List<String> getAllLines() throws IOException {
		return filename == null ? new ArrayList<String>() : Files.readAllLines(new File(filename).toPath(), Charset.forName("UTF-8"));
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(filename);
	}
}
