package cj.cloudwave.member.exception;

public class IdDuplicateException extends RuntimeException {
    public IdDuplicateException() {
        super();
    }

    public IdDuplicateException(String message) {
        super(message);
    }

    public IdDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdDuplicateException(Throwable cause) {
        super(cause);
    }

    protected IdDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
