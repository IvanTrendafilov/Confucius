package org.trendafilov.confucius.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Test;
import org.trendafilov.confucius.Configuration;
import org.trendafilov.confucius.core.ConfigUtils;

public class ConfigUtilsTest {

	@Test
	public void sanityCheck() {
		Map<String, String> properties = ConfigUtils.getSystemProperties();
		assertTrue(properties.size() > 0);
		assertNotNull(properties.get("java.vm.version"));
	}

	@Test
	public void testReset() {
		Map<String, String> before = ConfigUtils.getSystemProperties();
		Configuration.getInstance().setProperty("123123", "test-value");
		Configuration.getInstance().setProperty("test-key-123", "test-value");
		Configuration.getInstance().reset();
		Map<String, String> now = ConfigUtils.getSystemProperties();
		assertTrue(before.size() == now.size() && now.size() > 0);
		for (Entry<String, String> entry : before.entrySet())
			assertEquals(entry.getValue(), now.get(entry.getKey()));
	}

	@After
	public void tearDown() {
		Configuration.getInstance().reset();
	}
}
