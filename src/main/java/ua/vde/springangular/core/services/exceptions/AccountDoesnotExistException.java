package ua.vde.springangular.core.services.exceptions;

/**
 * Created by E5520 on 19.03.2017.
 */
public class AccountDoesnotExistException extends RuntimeException {
    public AccountDoesnotExistException() {
    }

    public AccountDoesnotExistException(String message) {
        super(message);
    }

    public AccountDoesnotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesnotExistException(Throwable cause) {
        super(cause);
    }
}
