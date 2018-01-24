package cz.zcu.students.kiwi.redebtr.helpers;

import java.util.Arrays;

public class ExceptionHelper {
    public static String stringifyStackTrace(Exception ex) {
        String[] traces = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .toArray(String[]::new);

        return String.join("\n", traces);
    }

}
