package org.trendafilov.confucius.core;

public class ConfigurationException extends Exception {

	private static final long serialVersionUID = -3389731343192490718L;

	public ConfigurationException() {
		super();
	}
	
	public ConfigurationException(String message) {
		super(message);
	}
	
	public ConfigurationException(Throwable cause) {
		super(cause);
	}
	
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
}
