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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class StreamConfigurationDataProvider implements ConfigurationDataProvider {

	private InputStream inputStream;

	public StreamConfigurationDataProvider(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<String> getAllLines() throws IOException {
		if (inputStream == null)
			return new ArrayList<>();
		String configurationString = Utils.streamToString(inputStream);
		this.inputStream = new ByteArrayInputStream(configurationString.getBytes("UTF-8"));
		return new ArrayList<>(Arrays.asList(configurationString.split("\\r?\\n")));
	}

	public InputStream getInputStream() throws IOException {
		return inputStream;
	}
}
