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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class StreamConfigurationDataProviderTest {

	@Test
	public void testReturnInputStream() throws IOException {
		InputStream inputStream = new ByteArrayInputStream("contents".getBytes("UTF-8"));
		StreamConfigurationDataProvider provider = new StreamConfigurationDataProvider(inputStream);
		assertEquals("contents", Utils.streamToString(provider.getInputStream()));
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

	@Test
	public void testResettingStreamAfterGetAllLines() throws IOException {
		InputStream inputStream = new ByteArrayInputStream("contents".getBytes("UTF-8"));
		StreamConfigurationDataProvider provider = new StreamConfigurationDataProvider(inputStream);
		provider.getAllLines();
		assertEquals("contents", Utils.streamToString(provider.getInputStream()));
	}
}
