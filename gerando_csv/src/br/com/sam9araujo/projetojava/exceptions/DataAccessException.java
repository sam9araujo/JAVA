package br.com.sam9araujo.projetojava.exceptions;

public class DataAccessException extends Exception{

	private static final long serialVersionUID = -6651474823775298547L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
