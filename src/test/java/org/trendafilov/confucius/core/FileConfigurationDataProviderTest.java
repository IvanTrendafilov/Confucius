package org.trendafilov.confucius.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class FileConfigurationDataProviderTest extends TestCase {
	private final static String FILENAME = System.getProperty("java.io.tmpdir") + File.separator + "fileprovidertest";

	@Test
	public void testReturnInputStream() throws IOException {
		createFile();

		FileConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		assertEquals("a\nb\r\nc\n", Utils.streamToString(provider.getInputStream()));
	}

	@Test
	public void testReturnEmptyListWhenFileNameIsNull() throws IOException {
		FileConfigurationDataProvider provider = new FileConfigurationDataProvider(null);

		List<String> lines = provider.getAllLines();
		assertNotNull(lines);
		assertEquals(0, lines.size());
	}

	@Test
	public void testReturnLines() throws IOException {
		createFile();

		FileConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		List<String> lines = provider.getAllLines();
		assertEquals(3, lines.size());
		assertEquals("a", lines.get(0));
		assertEquals("b", lines.get(1));
		assertEquals("c", lines.get(2));
	}

	private void createFile() {
		try {
			PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
			writer.print("a\nb\r\nc\n");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

	}

	@After
	public void tearDown() {
		new File(FILENAME).delete();
	}

}