package springangular.core.services.exceptions;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogExistsException extends RuntimeException {
    public BlogExistsException() {
        super();
    }

    public BlogExistsException(String message) {
        super(message);
    }

    public BlogExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
