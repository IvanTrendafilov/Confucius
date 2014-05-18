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

import org.junit.After;
import org.junit.Test;
import org.trendafilov.confucius.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;

public class UtilsTest {

	@Test
	public void sanityCheck() {
		Map<String, String> properties = Utils.propertiesToMap(System.getProperties());
		assertTrue(properties.size() > 0);
		assertNotNull(properties.get("java.vm.version"));
	}

	@Test
	public void testReset() {
		Map<String, String> before = Utils.propertiesToMap(System.getProperties());
		Configuration.getInstance().setProperty("123123", "test-value");
		Configuration.getInstance().setProperty("test-key-123", "test-value");
		Configuration.getInstance().reset();
		Map<String, String> now = Utils.propertiesToMap(System.getProperties());
		assertTrue(before.size() == now.size() && now.size() > 0);
		for (Entry<String, String> entry : before.entrySet())
			assertEquals(entry.getValue(), now.get(entry.getKey()));
	}

	@Test
	public void testStreamToString() throws IOException {
		InputStream inputStream = new ByteArrayInputStream("contents".getBytes("UTF-8"));
		assertEquals("contents", Utils.streamToString(inputStream));
	}

	@After
	public void tearDown() {
		Configuration.getInstance().reset();
	}
}
