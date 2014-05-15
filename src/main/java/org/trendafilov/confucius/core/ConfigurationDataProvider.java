package org.trendafilov.confucius.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ConfigurationDataProvider {
    public List<String> getAllLines() throws IOException;

    public InputStream getInputStream() throws IOException;
}
