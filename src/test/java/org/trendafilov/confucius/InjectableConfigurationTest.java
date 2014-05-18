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

package org.trendafilov.confucius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

public class InjectableConfigurationTest {
	private final static String TEST_CONTEXT = "Test2";
	
	@Test
	public void sanityCheck() {
		InjectableConfiguration config = new InjectableConfiguration();
		assertFalse(config.keySet().isEmpty());
	}
	
	@Test
	public void testTwoArgsConstructorUsingFilename() throws IOException {
		File temp = writeFile(true);
		InjectableConfiguration config = new InjectableConfiguration(temp.getAbsolutePath(), TEST_CONTEXT);
		assertFalse(config.keySet().isEmpty());
		assertEquals(TEST_CONTEXT, config.getStringValue("conf.context"));
		assertEquals(temp.getAbsolutePath(), config.getStringValue("conf.properties"));
		assertEquals("value123", config.getStringValue("key123"));
		assertEquals("value456", config.getStringValue("key456"));
		temp.delete();
	}

	@Test
	public void testTwoArgsConstructorUsingInputStream() throws IOException {
		StringBuilder conf = new StringBuilder("[Default]");
		conf.append("\n");
		conf.append("key123=value123");
		InputStream inputStream = new ByteArrayInputStream(conf.toString().getBytes("UTF-8"));
		InjectableConfiguration config = new InjectableConfiguration(inputStream, "Default");
		assertFalse(config.keySet().isEmpty());
		assertEquals("value123", config.getStringValue("key123"));
	}

	@Test
	public void testTwoArgsConstructorWithoutContext() throws IOException {
		File temp = writeFile(false);
		InjectableConfiguration config = new InjectableConfiguration(temp.getAbsolutePath(), null);
		assertFalse(config.keySet().isEmpty());
		assertEquals(temp.getAbsolutePath(), config.getStringValue("conf.properties"));
		assertEquals("value123", config.getStringValue("key123"));
		assertEquals("value456", config.getStringValue("key456"));
		temp.delete();
	}
	
	@After
	public void tearDown() {
		System.clearProperty("conf.properties");
		System.clearProperty("conf.context");
	}
	
	private File writeFile(boolean hasContext) throws IOException {
		File temp = File.createTempFile("confuciusTest", ".tmp");
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		if (hasContext)
			bw.write("[Default]");
		bw.newLine();
		bw.write("key123=value123");
		bw.newLine();
		bw.newLine();
		if (hasContext)
			bw.write("[" + TEST_CONTEXT + "]");
		bw.newLine();
		bw.write("key456=value456");
		bw.close();
		return temp;
	}
}
