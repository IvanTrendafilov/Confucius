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
		super();
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
