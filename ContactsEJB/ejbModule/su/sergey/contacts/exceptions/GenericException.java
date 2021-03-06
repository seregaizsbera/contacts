package su.sergey.contacts.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * GenericException
 * 
 * @author ������ ��������
 */
abstract public class GenericException extends Exception {
	private Throwable parentException;
	
	public GenericException() {}
	
	public GenericException(String message) {
		super(message);
	}

	/**
	 * Constructor for GenericException
	 */
	public GenericException(Throwable parentException) {
		super(ExceptionUtil.getMessage(parentException));
		this.parentException = ExceptionUtil.followExceptionChain(parentException);
	}
	
	/**
	 * Constructor for GenericException
	 */
	public GenericException(String message, Throwable parentException) {
		super(message);
		this.parentException = ExceptionUtil.followExceptionChain(parentException);
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
