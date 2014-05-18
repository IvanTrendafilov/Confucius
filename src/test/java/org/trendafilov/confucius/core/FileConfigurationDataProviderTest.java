/* 
 * Copyright 2013-2014 Ivan Trendafilov and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

	@After
	public void tearDown() {
		new File(FILENAME).delete();
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
}