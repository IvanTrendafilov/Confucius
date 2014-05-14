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

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

class Parser {
	private final static String DEFAULT_CONTEXT = "Default";
	private final static String COMMENT = "#";
	private final static String IDENTITY = "=";
	private final static String LEFT_CONTEXT = "[";
	private final static String RIGHT_CONTEXT = "]";
	private final static String LEFT_SUBSTITUTION = "${";
	private final static String RIGHT_SUBSTITUTION = "}";
    private final static String INHERITANCE = ":";

    private final Map<String, String> configuration = new HashMap<>();

    public Parser(ConfigurationDataProvider configurationDataProvider, String context) {
        try {
            Collection<String> lines = configurationDataProvider.getAllLines();
            if (!lines.isEmpty() && isStandardProps(lines)) {
                loadStandardProps(configurationDataProvider);
            } else {
                List<String> contextPath = gatherInheritancePath(context, lines);
                Collections.reverse(contextPath);
                for (String contextToParse : contextPath) {
                    parseContext(lines, contextToParse);
                }
            }
            parseVariables();

        } catch (IOException e) {
            throw new ConfigurationException("Unable to read configuration", e);
        }
    }


    private List<String> gatherInheritancePath(String context, Collection<String> lines) {
        List<String> path = new ArrayList<>();

        if (DEFAULT_CONTEXT.equalsIgnoreCase(context)) {
            path.add(DEFAULT_CONTEXT);
        } else {
            for (String line : lines) {
                if (isContext(line) && isNamedContext(line, context)) {
                    path.add(context);
                    String parent = getParentWithoutContext(line);
                    path.addAll(gatherInheritancePath(parent, lines));
                }
            }
        }

        if (path.isEmpty()) {
            path.add(DEFAULT_CONTEXT);
        }

        return path;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
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

    private void loadStandardProps(ConfigurationDataProvider provider) throws IOException {
        Properties props = new Properties();
        props.load(provider.getInputStream());
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
        return context != null && getContextWithoutParent(line).equalsIgnoreCase(LEFT_CONTEXT + context + RIGHT_CONTEXT);
    }

    private String getContextWithoutParent(String line) {
        if (!line.contains(INHERITANCE)) {
            return line.trim();
        }
        String[] parts = line.split(INHERITANCE);
        return parts[0].trim() + "]";
    }

    private String getParentWithoutContext(String line) {
        if (!line.contains(INHERITANCE)) {
            return DEFAULT_CONTEXT;
        }

        String[] parts = line.split(INHERITANCE);
        return parts[1].trim().substring(0, parts[1].indexOf(RIGHT_CONTEXT)).trim();

    }

    private boolean isSubstitution(String value) {
		return value.startsWith(LEFT_SUBSTITUTION) && value.endsWith(RIGHT_SUBSTITUTION);
	}

	private String getSubstitution(String value) {
		return value.substring(2, value.length() - 1);
	}
}
