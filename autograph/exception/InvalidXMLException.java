package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when the imported XML is invalid
 * 
 * @author Ben List
 * @version 1.0
 * 
 * @param err - string to hold the error message
 * @param pCause - parent exception
 *
 */
public class InvalidXMLException extends Exception {
	
	String err;
	Throwable pCause;
	
	InvalidXMLException() {
		super();
		err = "unknown";
	}
	public InvalidXMLException(String description) {
		super(description);
		err = description;
	}
	public InvalidXMLException(Throwable cause) { 
		super(cause); 
		pCause = cause;
	} 
	public InvalidXMLException(String description, Throwable cause) { 
		super(description, cause); 
		err = description;
		pCause = cause;
	}
	
	// Return the error message (used when catching the
	// error) (Not necessary when throwing a new error)
	public String getError() {
		return err;
	}
	
	// Return the parent exception (used when catching the
	// error) (Not necessary when throwing a new error)
	public Throwable getCause() {
		return pCause;
	}
}
