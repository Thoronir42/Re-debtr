package cz.zcu.students.kiwi.libs.security.registration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CurrentDateQuestion extends AQuestion {
    private final Type type;

    public CurrentDateQuestion(Type type) {
        this.type = type;
    }

    @Override
    public String getPrompt() {
        return String.format("Number of current %s (e.g. %s)", type.name(), type.getExample());
    }

    @Override
    public String getAnswer() {
        return new SimpleDateFormat(type.getFormatString()).format(new Date());
    }

    public enum Type {
        Day("d", "06"), Week("w", "42"), Month("M", "01"), Year("y", "2001");


        private final String formatString;
        private final String example;

        Type(String formatString, String example) {

            this.formatString = formatString;
            this.example = example;
        }

        public String getFormatString() {
            return formatString;
        }

        public String getExample() {
            return example;
        }
    }

    public static CurrentDateQuestion generateQuestion(Random rand) {
        Type t = Type.values()[rand.nextInt(Type.values().length)];
        return new CurrentDateQuestion(t);
    }
}
