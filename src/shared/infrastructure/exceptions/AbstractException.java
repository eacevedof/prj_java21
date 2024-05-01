package shared.infrastructure.exceptions;


public abstract class AbstractException extends Exception{
    private final int statusCode;

    public AbstractException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
