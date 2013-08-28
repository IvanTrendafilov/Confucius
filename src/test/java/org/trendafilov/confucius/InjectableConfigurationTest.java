package org.trendafilov.confucius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.trendafilov.confucius.InjectableConfiguration;
import org.trendafilov.confucius.core.ConfigurationException;

public class InjectableConfigurationTest {
	private final static String TEST_CONTEXT = "Test2";

	@Test
	public void sanityCheck() {
		InjectableConfiguration config = new InjectableConfiguration();
		assertFalse(config.keySet().isEmpty());
	}

	@Test
	public void testTwoArgsConstructor() throws ConfigurationException {
		InjectableConfiguration config = new InjectableConfiguration("some-file.cfg", TEST_CONTEXT);
		assertFalse(config.keySet().isEmpty());
		assertEquals(TEST_CONTEXT, config.getStringValue("conf.context"));
	}
}
