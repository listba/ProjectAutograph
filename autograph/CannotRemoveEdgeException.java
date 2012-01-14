package autograph;

import java.lang.Exception;

/**
 * Exception to handle errors when removing an edge
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotRemoveEdgeException extends Exception {
	CannotRemoveEdgeException() {
		super();
	}
	CannotRemoveEdgeException(String description) {
		super(description);
	}
}