package org.trendafilov.confucius.core;

public class RuntimeConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 2909522377132243053L;

	public RuntimeConfigurationException() {
		super();
	}

	public RuntimeConfigurationException(String message) {
		super(message);
	}

	public RuntimeConfigurationException(Throwable cause) {
		super(cause);
	}

	public RuntimeConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
}
