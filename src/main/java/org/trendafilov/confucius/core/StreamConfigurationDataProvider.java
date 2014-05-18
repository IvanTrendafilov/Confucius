package org.trendafilov.confucius.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamConfigurationDataProvider implements ConfigurationDataProvider {

	InputStream inputStream;

	public StreamConfigurationDataProvider(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public List<String> getAllLines() throws IOException {
		if (inputStream == null) {
			return new ArrayList<>();
		}

		String configurationString = Utils.streamToString(inputStream);
		this.inputStream = new ByteArrayInputStream(configurationString.getBytes("UTF-8"));
		return new ArrayList<>(Arrays.asList(configurationString.split("\\r?\\n")));
	}


	@Override
	public InputStream getInputStream() throws IOException {
		return inputStream;
	}


}
