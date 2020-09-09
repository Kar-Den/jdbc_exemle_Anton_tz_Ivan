package by.it_academy.jdbc.app.exception;

public class ApplicationBaseException extends RuntimeException {

    public ApplicationBaseException() {
        super();
    }

    public ApplicationBaseException(String message) {
        super(message);
    }

    public ApplicationBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
