/*
 * PicoXLSX4j is a small Java library to generate XLSX (Microsoft Excel 2007 or newer) files in an easy and native way
 * Copyright Raphael Stoeckli © 2015
 * This library is licensed under the MIT License.
 * You find a copy of the license in project folder or on: http://opensource.org/licenses/MIT
 */
package picoxlsx4j.exception;

/**
 * Class for exceptions regarding an unsupported data type
 * @author Raphael Stoeckli
 */
public class UnsupportedDataTypeException extends RuntimeException{
    
    /**
     * Default constructor
     */    
    public UnsupportedDataTypeException()
    {
        super();
    }
    
    /**
     * Constructor with passed message
     * @param message Message of the exception
     */    
    public UnsupportedDataTypeException(String message)
    {
        super(message);
    }
    
    
}
