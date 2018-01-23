package cz.zcu.students.kiwi.libs.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AStatusException extends RuntimeException {
    private final HttpStatus status;

    public AStatusException(HttpStatus status) {
        this.status = status;
    }

    public AStatusException(HttpStatus status, String message) {
        super(message);

        this.status = status;
    }



    public HttpStatus getStatus() {
        return status;
    }
}
