package springangular.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by E5520 on 19.03.2017.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestExeption extends RuntimeException {
    public BadRequestExeption() {
    }

    public BadRequestExeption(String message) {
        super(message);
    }

    public BadRequestExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestExeption(Throwable cause) {
        super(cause);
    }
}
