package br.com.hpe.cardsacquier.exceptions;

public class ValidationException extends Exception{

	private static final long serialVersionUID = -8667983412707853113L;	
	private int erroCode;
	
	public ValidationException(){
		super();
	}
	
	public ValidationException(String message, Throwable cause, int erroCode){
		super(message, cause);
		this.erroCode = erroCode;
	}
	
	public ValidationException(String message, int erroCode){		
		super(message);
		this.erroCode = erroCode;
	}
	
	public ValidationException(Throwable cause, int erroCode){
		super(cause);
		this.erroCode = erroCode;
	}
	
	public int getErroCode(){
		return this.erroCode;
	}
}
