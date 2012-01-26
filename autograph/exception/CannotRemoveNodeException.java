package autograph.exception;

import java.lang.Exception;

/**
 * Exception to handle errors when removing a node
 * 
 * @author Jeff Farris
 * @version 1.0
 *
 */
public class CannotRemoveNodeException extends Exception {
	CannotRemoveNodeException() {
		super();
	}
	CannotRemoveNodeException(String description) {
		super(description);
	}
}