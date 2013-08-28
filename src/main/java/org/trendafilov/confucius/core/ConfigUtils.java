package org.trendafilov.confucius.core;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class ConfigUtils {
	private ConfigUtils() { }

	public static Map<String, String> getSystemProperties() {
		Map<String, String> systemProperties = new HashMap<>();
		Properties props = System.getProperties();
		for (Enumeration<?> e = props.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String value = props.getProperty(key);
			systemProperties.put(key, value);
		}
		return systemProperties;
	}

	public static Map<String, String> getConfiguration(String filename,
			String context) {
		return new Parser(filename, context).getConfiguration();
	}
}
