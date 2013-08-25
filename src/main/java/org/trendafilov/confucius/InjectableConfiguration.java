package org.trendafilov.confucius;

import org.trendafilov.confucius.core.AbstractConfiguration;

/**
 * It is intended that this class will be used by Dependency Injection
 * frameworks to allow clients to control the scope of a <tt>Configurable</tt>
 * singleton.
 * 
 * <p>
 * This is required, as the scope of a singleton instance in a DI container may differ
 * from the scope of a self-managed singleton instance.
 * </p>
 * 
 * @author  Ivan Trendafilov
 * @since   1.0
 * @see		Configurable
 * @see		Configuration
 */
public class InjectableConfiguration extends AbstractConfiguration {
	
	public InjectableConfiguration() {
		super.init();
	}
	
	public InjectableConfiguration(String filePath, String context) {
		setProperty(FILE_PARAM, filePath);
		setProperty(CONTEXT_PARAM, context);
		super.init();
	}
}
