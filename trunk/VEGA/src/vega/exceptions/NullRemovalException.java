package vega.exceptions;

public class NullRemovalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7389672361730886016L;

	public NullRemovalException(){
		super();
	}
	
	public NullRemovalException(String message){
		super(message);
	}
	
}
