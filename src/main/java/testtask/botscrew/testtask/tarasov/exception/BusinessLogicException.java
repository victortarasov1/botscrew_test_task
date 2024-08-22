package testtask.botscrew.testtask.tarasov.exception;

public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
