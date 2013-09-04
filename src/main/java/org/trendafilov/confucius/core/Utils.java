package org.trendafilov.confucius.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Utils {
	private Utils() { }
	
	static Map<String, String> propertiesToMap(Properties props) {
		Map<String, String> properties = new HashMap<>();
		for (Object e : props.keySet()) {
			String key = (String) e;
			String value = props.getProperty(key);
			properties.put(key, value);
		}
		return properties;
	}
}
