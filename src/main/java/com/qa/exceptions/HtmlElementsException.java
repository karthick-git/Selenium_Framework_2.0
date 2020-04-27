package com.qa.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HtmlElementsException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;
	public static Logger logger = LogManager.getLogger(HtmlElementsException.class);
	public HtmlElementsException() {
        super();
    }

    public HtmlElementsException(String message) {
        super(message);
        logger.fatal(message);
    }

    public HtmlElementsException(String message, Throwable cause) {
        super(message, cause);
        logger.fatal(message, cause);
    }

    public HtmlElementsException(Throwable cause) {
        super(cause);
        logger.fatal(cause);
    }
}
