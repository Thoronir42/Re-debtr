package cz.zcu.students.kiwi.libs.validator;

public class StringValidator {
    public static final String NAME_PATTERN = "[\\w]+";
    public static final String EMAIL_PATTERN = "[\\w\\d]+(.[\\w\\d]+)*@[\\w\\d]+([\\w\\d]+)*";

    public static boolean name(String test){
        return test.matches(NAME_PATTERN);
    }

    public static boolean mail(String test) {
        return test.matches(EMAIL_PATTERN);
    }
}
