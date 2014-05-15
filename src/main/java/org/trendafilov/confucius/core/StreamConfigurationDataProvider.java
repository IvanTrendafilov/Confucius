package org.trendafilov.confucius.core;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
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

        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        String configurationString = writer.toString();

        this.inputStream = new ByteArrayInputStream(configurationString.getBytes("UTF-8"));
        return new ArrayList<>(Arrays.asList(configurationString.split("\\r?\\n")));
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }
}
