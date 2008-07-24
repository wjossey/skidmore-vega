package vega.exceptions;

public class InsertOutOfBoundsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8697503235239258909L;

	public InsertOutOfBoundsException(){
		super();
	}
	
	public InsertOutOfBoundsException(String message){
		super(message);
	}

}
