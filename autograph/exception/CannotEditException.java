package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when user attempts to edit
 * an object, but their requested edit is erroneous
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotEditException extends Exception {	
	CannotEditException() {
		super();
	}
	public CannotEditException(String description) {
		super(description);
	}
	public CannotEditException(Throwable cause) { 
		super(cause); 
	} 
	public CannotEditException(String description, Throwable cause) { 
		super(description, cause); 
	}
}