package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when trying to create a graph
 * 
 * @author Keith Wentzel
 * @version 1.0
 *
 */
public class CannotCreateGraphException extends Exception {
	CannotCreateGraphException() {
		super();
	}
	public CannotCreateGraphException(String description) {
		super(description);
	}
	public CannotCreateGraphException(Throwable cause) { 
		super(cause); 
	} 
	public CannotCreateGraphException(String description, Throwable cause) { 
		super(description, cause); 
	}
}