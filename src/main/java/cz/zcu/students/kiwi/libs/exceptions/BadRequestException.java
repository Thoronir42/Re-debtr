package cz.zcu.students.kiwi.libs.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AStatusException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
