package org.trendafilov.confucius.core;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StreamConfigurationDataProviderTest extends TestCase {

    @Test
    public void testReturnInputStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("contents".getBytes("UTF-8"));

        StreamConfigurationDataProvider provider = new StreamConfigurationDataProvider(inputStream);
        assertSame(inputStream, provider.getInputStream());
    }

    @Test
    public void testReturnEmptyListWhenInputStreamIsNull() throws IOException {
        StreamConfigurationDataProvider provider = new StreamConfigurationDataProvider(null);

        List<String> lines = provider.getAllLines();
        assertNotNull(lines);
        assertEquals(0, lines.size());
    }

    @Test
    public void testReturnLines() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("a\nb\r\nc\n".getBytes("UTF-8"));

        StreamConfigurationDataProvider provider = new StreamConfigurationDataProvider(inputStream);
        List<String> lines = provider.getAllLines();
        assertEquals(3, lines.size());
        assertEquals("a", lines.get(0));
        assertEquals("b", lines.get(1));
        assertEquals("c", lines.get(2));
    }
}
