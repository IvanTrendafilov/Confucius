/* 
 * Copyright 2013 Ivan Trendafilov
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

class Parser {
	private final static String DEFAULT_CONTEXT = "Default";
	private final static String COMMENT = "#";
	private final static String IDENTITY = "=";
	private final static String LEFT_CONTEXT = "[";
	private final static String RIGHT_CONTEXT = "]";
	private final static String LEFT_SUBSTITUTION = "${";
	private final static String RIGHT_SUBSTITUTION = "}";

	private final Map<String, String> configuration = new HashMap<>();

	public Parser(String filename, String context) {
		try {
			Collection<String> lines = readLines(filename);
			if (filename != null && isStandardProps(lines)) {
				loadStandardProps(filename);
			} else {
				parseContext(lines, DEFAULT_CONTEXT);
				parseContext(lines, context);
			}
			parseVariables();
		} catch (IOException e) {
			throw new ConfigurationException("Unable to read configuration file", e);
		}
	}

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	private Collection<String> readLines(String filename) throws IOException {
		List<String> lines = new ArrayList<>();
		if (filename == null)
			return lines;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null)
			lines.add(line);
		br.close();
		return lines;
	}

	private Map<String, String> parseLine(String line) {
		Map<String, String> pair = new HashMap<>();
		String newLine = line.trim();
		if (line.contains(COMMENT))
			newLine = newLine.substring(0, line.indexOf(COMMENT)).trim();
		if (newLine.isEmpty())
			return pair;
		if (newLine.contains(IDENTITY)) {
			String key = newLine.substring(0, newLine.indexOf(IDENTITY)).trim();
			String value = newLine.substring(newLine.indexOf(IDENTITY) + 1, newLine.length()).trim();
			pair.put(key, value);
			return pair;
		} else {
			throw new ConfigurationException(String.format("Unparsable line: [%s]", line));
		}
	}

	private boolean isStandardProps(Collection<String> lines) {
		for (String line : lines)
			if (isContext(line))
				return false;
		return true;
	}
	
	private void loadStandardProps(String filename) throws IOException {
		Properties props = new Properties();
		InputStream input = new FileInputStream(filename);
		props.load(input);
		configuration.putAll(Utils.propertiesToMap(props));
	}

	private void parseContext(Collection<String> lines, String context) {
		boolean insideContext = false;
		for (String line : lines) {
			if (isNamedContext(line, context))
				insideContext = true;
			else if (insideContext && isContext(line))
				insideContext = false;
			else if (insideContext)
				configuration.putAll(parseLine(line));
		}
	}

	private void parseVariables() {
		int previousSize = 0;
		Map<String, String> unresolved = new HashMap<>();
		for (Entry<String, String> entry : configuration.entrySet())
			if (isSubstitution(entry.getValue()))
				unresolved.put(entry.getKey(), getSubstitution(entry.getValue()));
		while (previousSize != unresolved.size()) {
			previousSize = unresolved.size();
			List<String> resolved = new ArrayList<>();
			for (Entry<String, String> entry : unresolved.entrySet())
				if (configuration.containsKey(entry.getValue()) && !unresolved.containsKey(entry.getValue())) {
					resolved.add(entry.getKey());
					configuration.put(entry.getKey(), configuration.get(entry.getValue()));
				}
			for (String item : resolved)
				unresolved.remove(item);
		}
	}

	private boolean isContext(String line) {
		line = line.trim();
		return line.startsWith(LEFT_CONTEXT) && line.endsWith(RIGHT_CONTEXT);
	}

	private boolean isNamedContext(String line, String context) {
		return context != null && line.trim().equalsIgnoreCase(LEFT_CONTEXT + context + RIGHT_CONTEXT);
	}

	private boolean isSubstitution(String value) {
		return value.startsWith(LEFT_SUBSTITUTION) && value.endsWith(RIGHT_SUBSTITUTION);
	}

	private String getSubstitution(String value) {
		return value.substring(2, value.length() - 1);
	}
}
