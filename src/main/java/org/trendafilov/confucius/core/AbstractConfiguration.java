package org.trendafilov.confucius.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trendafilov.confucius.Configurable;

public abstract class AbstractConfiguration implements Configurable {
	private final static Logger LOG = LoggerFactory.getLogger(AbstractConfiguration.class);

	private final static String ITEM_SEPARATOR = ",";
	protected static String FILE_PARAM = "conf.properties";
	protected static String CONTEXT_PARAM = "conf.context";

	private final static String FILE_PATH;
	private final static String CONTEXT;
	private final static Map<String, String> INITIAL_STATE;

	private final Map<String, String> configuration = new ConcurrentHashMap<>();
	
	static {
		FILE_PATH = System.getProperty(FILE_PARAM);
		CONTEXT = System.getProperty(CONTEXT_PARAM);
		INITIAL_STATE = Collections.unmodifiableMap(ConfigUtils.getSystemProperties());
	}
	
	protected void init() {
		LOG.info("Initializing configuration...");
		setProperties(INITIAL_STATE);
		setProperties(ConfigUtils.getConfiguration(FILE_PATH, CONTEXT));
	}
	
	public Set<String> keySet() {
		return configuration.keySet();
	}
	
	public boolean getBooleanValue(String key) throws ConfigurationException {
		return Boolean.parseBoolean(getKey(key));
	}
	
	public boolean getBooleanValue(String key, boolean defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Boolean.parseBoolean(value);
	}
	
	public List<Boolean> getBooleanList(String key, String separator) throws ConfigurationException {
		List<Boolean> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Boolean.parseBoolean(value.trim()));
		return parts;
	}
	
	public List<Boolean> getBooleanList(String key) throws ConfigurationException {
		return getBooleanList(key, ITEM_SEPARATOR);
	}

	public byte getByteValue(String key) throws ConfigurationException {
		return Byte.parseByte(getKey(key));
	}
	
	public byte getByteValue(String key, byte defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Byte.parseByte(value);
	}
	
	public List<Byte> getByteList(String key, String separator) throws ConfigurationException {
		List<Byte> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Byte.parseByte(value.trim()));
		return parts;
	}
	
	public List<Byte> getByteList(String key) throws ConfigurationException {
		return getByteList(key, ITEM_SEPARATOR);
	}

	public char getCharValue(String key) throws ConfigurationException {
		return getKey(key).charAt(0);
	}
	
	public char getCharValue(String key, char defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : value.charAt(0);
	}
	
	public List<Character> getCharList(String key, String separator) throws ConfigurationException {
		List<Character> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(value.trim().charAt(0));
		return parts;
	}
	
	public List<Character> getCharList(String key) throws ConfigurationException {
		return getCharList(key, ITEM_SEPARATOR);
	}
	
	public double getDoubleValue(String key) throws ConfigurationException {
		return Double.parseDouble(getKey(key));
	}

	public double getDoubleValue(String key, double defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Double.parseDouble(value);
	}

	public List<Double> getDoubleList(String key, String separator) throws ConfigurationException {
		List<Double> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Double.parseDouble(value.trim()));
		return parts;
	}

	public List<Double> getDoubleList(String key) throws ConfigurationException {
		return getDoubleList(key, ITEM_SEPARATOR);
	}

	public float getFloatValue(String key) throws ConfigurationException {
		return Float.parseFloat(getKey(key));
	}
	
	public float getFloatValue(String key, float defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Float.parseFloat(value);
	}
	
	public List<Float> getFloatList(String key, String separator) throws ConfigurationException {
		List<Float> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator)) 
			parts.add(Float.parseFloat(value.trim()));
		return parts;
	}
	
	public List<Float> getFloatList(String key) throws ConfigurationException {
		return getFloatList(key, ITEM_SEPARATOR);
	}

	public int getIntValue(String key) throws ConfigurationException {
		return Integer.parseInt(getKey(key));
	}
	
	public int getIntValue(String key, int defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Integer.parseInt(value);
	}
	
	public List<Integer> getIntList(String key, String separator) throws ConfigurationException {
		List<Integer> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Integer.parseInt(value.trim()));
		return parts;
	}
	
	public List<Integer> getIntList(String key) throws ConfigurationException {
		return getIntList(key, ITEM_SEPARATOR);
	}

	public long getLongValue(String key) throws ConfigurationException {
		return Long.parseLong(getKey(key));
	}
	
	public long getLongValue(String key, long defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Long.parseLong(value);
	}
	
	public List<Long> getLongList(String key, String separator) throws ConfigurationException {
		List<Long> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Long.parseLong(value.trim()));
		return parts;
	}
	
	public List<Long> getLongList(String key) throws ConfigurationException {
		return getLongList(key, ITEM_SEPARATOR);
	}

	public short getShortValue(String key) throws ConfigurationException {
		return Short.parseShort(getKey(key));
	}
	
	public short getShortValue(String key, short defaultValue) {
		String value = configuration.get(key);
		return value == null ? defaultValue : Short.parseShort(value);
	}
	
	public List<Short> getShortList(String key, String separator) throws ConfigurationException {
		List<Short> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Short.parseShort(value.trim()));
		return parts;
	}
	
	public List<Short> getShortList(String key) throws ConfigurationException {
		return getShortList(key, ITEM_SEPARATOR);
	}

	public String getStringValue(String key) throws ConfigurationException {
		return getKey(key);
	}
	
	public String getStringValue(String key, String defautValue) {
		String value = configuration.get(key);
		return value == null ? defautValue : value;
	}
	
	public List<String> getStringList(String key, String separator) throws ConfigurationException {
		List<String> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(value.trim());
		return parts;
	}
	
	public List<String> getStringList(String key) throws ConfigurationException {
		return getStringList(key, ITEM_SEPARATOR);
	}

	public synchronized <T> void setProperty(String key, T value) {
		String item = value.toString();
		configuration.put(key, item);
		System.setProperty(key, item);
		LOG.info("Set configuration property: [{}] => [{}]", key, item);
	}
	
	public synchronized <T> void setProperties(Map<String, T> propertyMap) {
		for (Entry<String, T> entry : propertyMap.entrySet())
			setProperty(entry.getKey(), entry.getValue());
	}

	public synchronized void clearProperty(String key) {
		if (configuration.remove(key) != null) {
			System.clearProperty(key);
			LOG.info("Unset configuration property: [{}]", key);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>The reset procedure restores system configuration properties to 
	 * their initial values at the time of creation of the <tt>Configurable</tt>
	 * instance. Configuration properties specified via a file are re-processed.
	 * </p>
	 */
	public synchronized void reset() {
		for (String key : configuration.keySet())
			clearProperty(key);
		init();
		LOG.info("Configuration properties have been reset");
	}

	private String getKey(String key) throws ConfigurationException {
		String value = configuration.get(key);
		if (value == null)
			throw new ConfigurationException(String.format("Unable to find configuration value for key [%s]", key));
		return value;
	}
}
