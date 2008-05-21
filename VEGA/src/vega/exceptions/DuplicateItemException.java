package vega.exceptions;

/**
 * Exception class for duplicate item errors
 * in search tree insertions.
 * @author Mark Allen Weiss
 */
public class DuplicateItemException extends RuntimeException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1704060696095872028L;
	/**
     * Construct this exception object.
     */
    public DuplicateItemException( )
    {
        super( );
    }
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public DuplicateItemException( String message )
    {
        super( message );
    }
}