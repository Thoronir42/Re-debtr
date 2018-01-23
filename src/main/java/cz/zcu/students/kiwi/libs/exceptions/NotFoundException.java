package cz.zcu.students.kiwi.libs.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AStatusException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
