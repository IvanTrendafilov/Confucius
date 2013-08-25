package org.trendafilov.confucius;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.trendafilov.confucius.core.ConfigurationException;

/**
 * The <tt>Configurable</tt> interface defines the contracts which are exposed 
 * publicly via the API to all clients of the framework.
 * 
 * @author  Ivan Trendafilov
 * @since   1.0
 */
public interface Configurable {
	
    /**
     * Returns a {@link Set} view of the keys contained in
     * the backing configuration map.
     * 
     * @return a set view of the keys contained in the backing configuration map
     */
	Set<String> keySet();
	
	/**
	 * Returns as a {@code boolean} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a boolean
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	boolean getBooleanValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code boolean} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a boolean, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	boolean getBooleanValue(String key, boolean defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of boolean values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Boolean> getBooleanList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of boolean values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */	
	List<Boolean> getBooleanList(String key) throws ConfigurationException;

	/**
	 * Returns as a {@code byte} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a byte
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	byte getByteValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code byte} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a byte, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	byte getByteValue(String key, byte defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of byte values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Byte> getByteList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of byte values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */	
	List<Byte> getByteList(String key) throws ConfigurationException;

	/**
	 * Returns as a {@code char} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a char
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	char getCharValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code char} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a char, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	char getCharValue(String key, char defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of char values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Character> getCharList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of char values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */	
	List<Character> getCharList(String key) throws ConfigurationException;

	/**
	 * Returns as a {@code double} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a double
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	double getDoubleValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code double} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a double, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	double getDoubleValue(String key, double defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of double values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Double> getDoubleList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of double values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Double> getDoubleList(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code float} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a float
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	float getFloatValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code float} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a float, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	float getFloatValue(String key, float defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of float values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Float> getFloatList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of float values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Float> getFloatList(String key) throws ConfigurationException;

	/**
	 * Returns as an {@code int} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as an int
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	int getIntValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as an {@code int} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as an int, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	int getIntValue(String key, int defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of int values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Integer> getIntList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of int values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Integer> getIntList(String key) throws ConfigurationException;

	/**
	 * Returns as a {@code long} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a long
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	long getLongValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code long} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a long, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	long getLongValue(String key, long defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of long values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Long> getLongList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of long values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Long> getLongList(String key) throws ConfigurationException;

	/**
	 * Returns as a {@code short} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a short
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	short getShortValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@code short} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a short, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	short getShortValue(String key, short defaultValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of short values, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Short> getShortList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of short values, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<Short> getShortList(String key) throws ConfigurationException;

	/**
	 * Returns as a {@link String} the configuration value to which 
	 * the specified key is mapped, or throws a {@code ConfigurationException} 
	 * if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @return associated value as a String
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	String getStringValue(String key) throws ConfigurationException;
	
	/**
	 * Returns as a {@link String} the configuration value to which
	 * the specified key is mapped, or returns the provided default value 
	 * argument if the specified key is missing in the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param defaultValue is returned if the backing map does not contain the passed 
	 * <b>key</b> parameter
	 * @return associated value as a String, or <b>defaultValue</b> if the backing map does
	 * not contain the <b>key</b>
	 */
	String getStringValue(String key, String defautValue);
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map.
	 * 
	 * @param key of the configuration property
	 * @param separator regular expression
	 * @return a list of strings, as separated on the <b>separator</b> regular expression
	 * @throws ConfigurationException if the backing map does not contain the key
	 */
	List<String> getStringList(String key, String separator) throws ConfigurationException;
	
	/**
	 * Returns a List of values mapped to the specified key, or throws
	 * a {@code ConfigurationException} if the specified key is missing in 
	 * the backing configuration map. Uses the comma character ("<b>,</b>") as 
	 * the regular expression for separation of items.
	 * 
	 * @param key of the configuration property
	 * @return a list of strings, as separated on the comma character ("<b>,</b>")
	 * @throws ConfigurationException if the backing map does not contain the key
	 */	
	List<String> getStringList(String key) throws ConfigurationException;
	
	/**
	 * Sets the configuration property indicated by the specified key.
	 * 
	 * <p>Where applicable, the value argument must override its {@code toString}
	 * method to provide the desired textually representative value to store
	 * as a Configuration property.
	 * </p>
	 * 
	 * @param key the name of the configuration property
	 * @param value the value of the configuration property
	 */
	<T> void setProperty(String key, T value);
	
	/**
	 * Sets the configuration properties as indicated by the specified map.
	 * 
	 * @param propertyMap a map of configuration properties
	 * @see #setProperty(String, T)
	 */
	<T> void setProperties(Map<String, T> propertyMap);

	/**
	 * Removes the configuration property indicated by the specified key.
	 * 
	 * @param key of the configuration property
	 */
	void clearProperty(String key);

	/**
	 * Resets all intermediate state held in the backing configuration map.
	 * 
	 * <p>It is advisable not to call this method during normal program flow. 
	 * Instead, use it to reset the state of a <tt>Configurable</tt> instance between 
	 * unit tests.
	 * </p>
	 */
	void reset();
}
