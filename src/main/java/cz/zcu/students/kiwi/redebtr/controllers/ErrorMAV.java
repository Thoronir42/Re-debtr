package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.ui.ModelMap;

public class ErrorMAV extends LayoutMAV {

    public ErrorMAV() {
        this(500);
    }

    public ErrorMAV(int errorCode) {
        this(errorCode, "Something went wrong");
    }

    public ErrorMAV(int errorCode, String message) {
        super("error/genericError.jsp");

        ModelMap model = this.getModelMap();

        model.put("errorStatusCode", errorCode);
        model.put("errorMessage", message);

    }
}
