package springangular.core.services.exceptions;

/**
 * Created by E5520 on 26.03.2017.
 */
public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException() {
        super();
    }

    public BlogNotFoundException(String message) {
        super(message);
    }

    public BlogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
