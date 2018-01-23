package cz.zcu.students.kiwi.libs.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends AStatusException{
    public ForbiddenException() {
        this("You are missing privileges to do that");
    }

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
