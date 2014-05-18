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

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * The <tt>Configurable</tt> interface defines the contracts which are exposed
 * publicly via the API to all clients of the framework.
 * 
 * @author Ivan Trendafilov
 * @since  1.0
 */
public interface Configurable {

	/**
	 * Returns a {@link Set} view of the keys contained in the configuration
	 * map.
	 * 
	 * @return a set view of the keys contained in the configuration properties
	 */
	Set<String> keySet();

	/**
	 * Returns as a {@code boolean} the configuration value to which the
	 * specified key is mapped, or throws an unchecked
	 * {@code ConfigurationException} if the specified key is missing in the
	 * configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a boolean
	 */
	boolean getBooleanValue(String key);

	/**
	 * Returns as a {@code boolean} the configuration value to which the
	 * specified key is mapped, or returns the provided default value argument
	 * if the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a boolean, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	boolean getBooleanValue(String key, boolean defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of boolean values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Boolean> getBooleanList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of boolean values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Boolean> getBooleanList(String key);

	/**
	 * Returns as a {@code byte} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a byte
	 */
	byte getByteValue(String key);

	/**
	 * Returns as a {@code byte} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a byte, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	byte getByteValue(String key, byte defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of byte values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Byte> getByteList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of byte values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Byte> getByteList(String key);

	/**
	 * Returns as a {@code char} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a char
	 */
	char getCharValue(String key);

	/**
	 * Returns as a {@code char} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a char, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	char getCharValue(String key, char defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of char values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Character> getCharList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of char values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Character> getCharList(String key);

	/**
	 * Returns as a {@code double} the configuration value to which the
	 * specified key is mapped, or throws an unchecked
	 * {@code ConfigurationException} if the specified key is missing in the
	 * configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a double
	 */
	double getDoubleValue(String key);

	/**
	 * Returns as a {@code double} the configuration value to which the
	 * specified key is mapped, or returns the provided default value argument
	 * if the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a double, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	double getDoubleValue(String key, double defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of double values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Double> getDoubleList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of double values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Double> getDoubleList(String key);

	/**
	 * Returns as a {@code float} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a float
	 */
	float getFloatValue(String key);

	/**
	 * Returns as a {@code float} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a float, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	float getFloatValue(String key, float defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of float values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Float> getFloatList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of float values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Float> getFloatList(String key);

	/**
	 * Returns as an {@code int} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as an int
	 */
	int getIntValue(String key);

	/**
	 * Returns as an {@code int} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as an int, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	int getIntValue(String key, int defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of int values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Integer> getIntList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of int values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Integer> getIntList(String key);

	/**
	 * Returns as a {@code long} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a long
	 */
	long getLongValue(String key);

	/**
	 * Returns as a {@code long} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a long, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	long getLongValue(String key, long defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of long values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Long> getLongList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of long values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Long> getLongList(String key);

	/**
	 * Returns as a {@code short} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a short
	 */
	short getShortValue(String key);

	/**
	 * Returns as a {@code short} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a short, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	short getShortValue(String key, short defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of short values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	List<Short> getShortList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of short values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<Short> getShortList(String key);

	/**
	 * Returns as a {@link String} the configuration value to which the
	 * specified key is mapped, or throws an unchecked
	 * {@code ConfigurationException} if the specified key is missing in the
	 * configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a String
	 */
	String getStringValue(String key);

	/**
	 * Returns as a {@link String} the configuration value to which the
	 * specified key is mapped, or returns the provided default value argument
	 * if the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a String, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	String getStringValue(String key, String defautValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of strings, as separated on the <b>separator</b> regular
	 *         expression
	 */
	List<String> getStringList(String key, String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of strings, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	List<String> getStringList(String key);

	/**
	 * Returns the current configuration properties.
	 * 
	 * @return properties
	 * 
	 * @see java.lang.System#getProperties()
	 * @see java.util.Properties
	 */
	Properties getProperties();

	/**
	 * Sets the configuration property indicated by the specified key.
	 * 
	 * <p>
	 * Where applicable, the value argument must override its {@code toString}
	 * method to provide the desired textually representative value to store as
	 * a Configuration property.
	 * </p>
	 * 
	 * @param key
	 *            the name of the configuration property
	 * @param value
	 *            the value of the configuration property
	 */
	<T> void setProperty(String key, T value);

	/**
	 * Sets the configuration properties as indicated by the specified map.
	 * 
	 * @param properties
	 *            a map of configuration properties
	 * @see #setProperty(String, T)
	 */
	<T> void setProperties(Map<String, T> properties);

	/**
	 * Sets the configuration properties to the <code>Properties</code>
	 * argument.
	 * 
	 * @param properties
	 *            the new system properties
	 * @see java.util.Properties
	 * @see java.lang.System#setProperties(Properties)
	 */
	void setProperties(Properties properties);

	/**
	 * Removes the configuration property indicated by the specified key.
	 * 
	 * @param key
	 *            of the configuration property
	 */
	void clearProperty(String key);

	/**
	 * Resets all intermediate state held in the configuration properties.
	 * 
	 * <p>
	 * It is advisable not to call this method during normal program flow.
	 * Instead, use it to reset the state of a <tt>Configurable</tt> instance
	 * between unit tests.
	 * </p>
	 */
	void reset();
}
