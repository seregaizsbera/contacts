package su.sergey.contacts.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * RuntimeGenericException
 * 
 * @author Сергей Богданов
 */
abstract public class RuntimeGenericException extends RuntimeException {
	private Throwable parentException;
	
	public RuntimeGenericException() {}
	
	public RuntimeGenericException(String message) {
		super(message);
	}

	/**
	 * Constructor for RuntimeGenericException
	 */
	public RuntimeGenericException(Throwable parentException) {
		super(parentException.getMessage());
		this.parentException = parentException;
	}
	
	/**
	 * Constructor for RuntimeGenericException
	 */
	public RuntimeGenericException(String message, Throwable parentException) {
		super(message);
		this.parentException = parentException;
	}
	
	/**
	 * Gets the parentException
	 * @return Returns a Throwable
	 */
	public Throwable getParent() {
		return parentException;
	}
	
	/**
	 * @see Throwable#printStackTrace()
	 */
	public void printStackTrace() {
		if (parentException != null) {
			parentException.printStackTrace();
			System.err.println("\n    The exception from above caused:");
		}
		super.printStackTrace();
	}

	/**
	 * @see Throwable#printStackTrace(PrintStream)
	 */
	public void printStackTrace(PrintStream out) {
		if (parentException != null) {
			parentException.printStackTrace(out);
			out.println("\n    The exception from above caused:");
		}
		super.printStackTrace(out);
	}

	/**
	 * @see Throwable#printStackTrace(PrintWriter)
	 */
	public void printStackTrace(PrintWriter out) {
		if (parentException != null) {
			parentException.printStackTrace(out);
			out.write("\n    The exception from above caused:\n");
		}
		super.printStackTrace(out);
	}
}
