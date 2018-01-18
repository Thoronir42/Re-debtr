package cz.zcu.students.kiwi.libs.servlet;

public class ErrorCodeException extends RuntimeException {
    private final int code;

    public ErrorCodeException() {
        this(500);
    }

    public ErrorCodeException(int code) {

        this.code = code;
    }
}
