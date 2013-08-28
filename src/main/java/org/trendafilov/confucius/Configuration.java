package org.trendafilov.confucius;

import org.trendafilov.confucius.core.AbstractConfiguration;

/**
 * A lazy and self-managed <tt>Configurable</tt> singleton. Unless dependency
 * injection for configuration is desired, this is the main point of entry for
 * clients into the framework.
 * 
 * @author Ivan Trendafilov
 * @since  1.0
 * @see    Configurable
 * @see    InjectableConfiguration
 */
public class Configuration extends AbstractConfiguration {

	private static volatile Configuration instance = null;

	private Configuration() {
		super.init();
	}

	public static Configuration getInstance() {
		if (instance == null)
			synchronized (Configuration.class) {
				if (instance == null)
					instance = new Configuration();
			}
		return instance;
	}
}
