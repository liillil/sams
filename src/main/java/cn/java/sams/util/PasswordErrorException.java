package cn.java.sams.util;
public class PasswordErrorException extends Exception {
    static final long serialVersionUID = -7034897190745766939L;

    
    public PasswordErrorException() {
        super();
    }

    public PasswordErrorException(String message) {
        super(message);
    }

    
    public PasswordErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    
    public PasswordErrorException(Throwable cause) {
        super(cause);
    }

   
    protected PasswordErrorException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
