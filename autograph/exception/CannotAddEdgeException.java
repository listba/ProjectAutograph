package autograph;

import java.lang.Exception;

/**
 * Exception to handle errors when trying to add an edge
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotAddEdgeException extends Exception {
	CannotAddEdgeException() {
		super();
	}
	CannotAddEdgeException(String description) {
		super(description);
	}
}