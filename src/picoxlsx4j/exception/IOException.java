/*
 * PicoXLSX4j is a small Java library to generate XLSX (Microsoft Excel 2007 or newer) files in an easy and native way
 * Copyright Raphael Stoeckli © 2015
 * This library is licensed under the MIT License.
 * You find a copy of the license in project folder or on: http://opensource.org/licenses/MIT
 */
package picoxlsx4j.exception;

/**
 * Class for exceptions regarding stream or save error incidents
 * @author Raphael Stoeckli
 */
public class IOException extends Exception{
    
    private Exception innerException;

     /**
     * Gets the inner exception
     * @return Inner exception
     */
    public Exception getInnerException() {
        return innerException;
    }
 
    /**
     * Default constructor
     */    
    public IOException()
    {
        super();
    }
    
    /**
     * Constructor with passed message
     * @param message Message of the exception
     */    
    public IOException(String message)
    {
        super(message);
    }
    
    /**
     * Constructor with passed message and inner exception
     * @param message Message of the exception
     * @param inner Inner exception
     */    
    public  IOException(String message, Exception inner)
    {
        super(message);
        this.innerException = inner;
    }
    
    
}
