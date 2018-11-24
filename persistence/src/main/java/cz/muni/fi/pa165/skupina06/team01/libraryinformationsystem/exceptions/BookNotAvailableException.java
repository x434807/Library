package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions;

/**
 *  Exception is thrown when require book for loan but book is not available
 * 
 * 
 * @author Andrej Sokolík
 */
public class BookNotAvailableException extends Exception {

    /**
     * Constructs an instance of <code>BookNotAvailableException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BookNotAvailableException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>BookNotAvailableException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     * @param cause the cause
     */
    public BookNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
