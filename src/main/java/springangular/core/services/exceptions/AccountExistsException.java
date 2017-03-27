package springangular.core.services.exceptions;

/**
 * Created by E5520 on 26.03.2017.
 */
public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
        super();
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
