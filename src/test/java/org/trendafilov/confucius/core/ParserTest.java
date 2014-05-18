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

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {
	private final static String FILENAME = System.getProperty("java.io.tmpdir") + File.separator + "ljctest.cfg";
	private final static String TEST_CONTEXT = "Test";

	@Test
	public void testValidConfigFile() {
		createFile(Collections.<String, String>emptyMap(), null, null);
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		assertTrue(new Parser(provider, null).getConfiguration().isEmpty());
	}

	@Test
	public void testNullConfig() {
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(null);
		assertTrue(new Parser(provider, null).getConfiguration().isEmpty());
	}

	@Test(expected = ConfigurationException.class)
	public void testMissingConfigFile() {
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME + "test");
		new Parser(provider, null).getConfiguration();
	}

	@Test(expected = ConfigurationException.class)
	public void testMissingConfigFileWithContext() {
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME + "test");
		new Parser(provider, TEST_CONTEXT);
	}

	@Test
	public void testAllEmptyContexts() {
		createFile(Collections.<String, String>emptyMap(), TEST_CONTEXT, Collections.<String, String>emptyMap());
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		assertTrue(new Parser(provider, TEST_CONTEXT).getConfiguration().isEmpty());
	}

	@Test
	public void testEmptyContext() {
		createFile(makeMap("key", "value"), TEST_CONTEXT, Collections.<String, String>emptyMap());
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertEquals("value", configuration.get("key"));
	}

	@Test
	public void testDefaultContext() {
		Map<String, String> map = makeMap("somekey", "somevalue", "newkey", "newvalue");
		createFile(map, null, null);
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, null).getConfiguration();
		assertTrue(map.size() == 2);
		for (Entry<String, String> entry : configuration.entrySet())
			assertEquals(map.get(entry.getKey()), entry.getValue());
	}

	@Test
	public void testBothContexts() {
		createFile(makeMap("somekey", "somevalue", "newkey", "newvalue"), TEST_CONTEXT, makeMap("test", "123"));
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertEquals("somevalue", configuration.get("somekey"));
		assertEquals("newvalue", configuration.get("newkey"));
		assertEquals("123", configuration.get("test"));
		assertTrue(configuration.size() == 3);
	}

	@Test
	public void testBothContextsWithOverride() {
		createFile(makeMap("somekey", "somevalue", "newkey", "newvalue"), TEST_CONTEXT, makeMap("newkey", "123"));
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertEquals("somevalue", configuration.get("somekey"));
		assertEquals("123", configuration.get("newkey"));
		assertTrue(configuration.size() == 2);
	}

	@Test
	public void testSubstitutionSameContext() {
		createFile(makeMap("key1", "value", "key2", "${key1}"), null, null);
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 2);
		assertEquals("value", configuration.get("key1"));
		assertEquals("value", configuration.get("key2"));
	}

	@Test
	public void testSubstitutionAcrossContexts() {
		createFile(makeMap("key1", "value"), TEST_CONTEXT, makeMap("key2", "${key1}"));
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 2);
		assertEquals("value", configuration.get("key1"));
		assertEquals("value", configuration.get("key2"));
	}

	@Test
	public void testSubstitutionChained() {
		createFile(makeMap("key0", "0", "key1", "value", "random", "no", "key2", "${key1}", "key3", "${key2}", "key4", "${key0}"), null, null);
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 6);
		assertEquals("0", configuration.get("key0"));
		assertEquals("value", configuration.get("key1"));
		assertEquals("no", configuration.get("random"));
		assertEquals("value", configuration.get("key2"));
		assertEquals("value", configuration.get("key3"));
		assertEquals("0", configuration.get("key4"));
	}

	@Test
	public void testSubstitutionChainedAcrossContexts() {
		createFile(makeMap("key1", "value", "key2", "${key1}"), TEST_CONTEXT, makeMap("key3", "${key2}", "key4", "${key0}"));
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 4);
		assertEquals("value", configuration.get("key1"));
		assertEquals("value", configuration.get("key2"));
		assertEquals("value", configuration.get("key3"));
		assertEquals("${key0}", configuration.get("key4"));
	}

	@Test
	public void testSubstitutionWithOverride() {
		createFile(makeMap("key0", "0", "key1", "value", "key2", "${key1}"), TEST_CONTEXT, makeMap("key2", "${key0}", "key3", "${key0}"));
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 4);
		assertEquals("0", configuration.get("key0"));
		assertEquals("value", configuration.get("key1"));
		assertEquals("0", configuration.get("key2"));
		assertEquals("0", configuration.get("key3"));
	}

	@Test
	public void testSubstitutionCircular() {
		createFile(makeMap("key1", "${key3}", "key2", "${key1}", "key3", "${key2}"), null, null);
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 3);
		assertEquals("${key3}", configuration.get("key1"));
		assertEquals("${key1}", configuration.get("key2"));
		assertEquals("${key2}", configuration.get("key3"));
	}

	@Test(expected = ConfigurationException.class)
	public void testUnparsableLine() throws Exception {
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
		writer.println("[Default]");
		writeLine(writer, makeMap("key1", "value1"));
		writer.println(" ");
		writer.println("Somestuff #");
		writer.close();
		new Parser(provider, null);
	}

	@Test
	public void testMultiContextRead() throws Exception {
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
		writer.println("[Default]");
		writeLine(writer, makeMap("key1", "value1")); // will be included
		writer.println("[" + TEST_CONTEXT + "-2]");
		writeLine(writer, makeMap("key2", "value2"));
		writer.println("[" + TEST_CONTEXT + "]");
		writeLine(writer, makeMap("key3", "value3")); // will be included
		writer.println("[" + TEST_CONTEXT + "-3]");
		writeLine(writer, makeMap("key4", "value4"));
		writer.close();
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 2);
		assertEquals("value3", configuration.get("key3"));
		assertEquals("value1", configuration.get("key1"));
	}

	@Test
	public void testLegacyFormat() throws Exception {
		String configurationString = new StringBuilder()
				.append("key1=value1\n")
				.append(" \n")
				.append("key3=value3\n").toString();
		InputStream inputStream = new ByteArrayInputStream(configurationString.getBytes("UTF-8"));

		ConfigurationDataProvider provider = new StreamConfigurationDataProvider(inputStream);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertTrue(configuration.size() == 2);
		assertEquals("value3", configuration.get("key3"));
		assertEquals("value1", configuration.get("key1"));
	}

	@Test
	public void testComplexString() {
		createFile(makeMap("key", "https://www.google.com/fp=dfc3525e9a3b356a&q=hello&safe=off/"), TEST_CONTEXT, Collections.<String, String>emptyMap());
		ConfigurationDataProvider provider = new FileConfigurationDataProvider(FILENAME);
		Map<String, String> configuration = new Parser(provider, TEST_CONTEXT).getConfiguration();
		assertEquals("https://www.google.com/fp=dfc3525e9a3b356a&q=hello&safe=off/", configuration.get("key"));
	}
	
	@After
	public void tearDown() {
		new File(FILENAME).delete();
	}
	
	private void createFile(Map<String, String> defaultPairs, String contextName, Map<String, String> contextPairs) {
		try {
			PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
			writer.println("[Default]");
			writeLine(writer, defaultPairs);
			if (contextName != null) {
				writer.println("[" + contextName + "]");
				writeLine(writer, contextPairs);
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeLine(PrintWriter writer, Map<String, String> context) {
		writeLine(writer, context, true);
	}

	private void writeLine(PrintWriter writer, Map<String, String> content, boolean hasComments) {
		for (Entry<String, String> pair : content.entrySet()) {
			StringBuilder line = new StringBuilder();
			line.append(pair.getKey()).append(" = ").append(pair.getValue());
			if (Math.random() <= 0.20 && hasComments)
				line.append(" # some random comment");
			writer.println(line.toString());
		}
	}

	private Map<String, String> makeMap(String... args) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i++)
			map.put(args[i], args[++i]);
		return map;
	}
}
