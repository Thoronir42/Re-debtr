package cz.zcu.students.kiwi.libs.security.registration;

import java.util.Random;

public class ArithmeticQuestion extends AQuestion {
    private final Operation op;
    private final int a;
    private final int b;

    public ArithmeticQuestion(Operation op, int a, int b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    @Override
    public String getPrompt() {
        return String.format("%d %s %d = ?", a, op.getSign(), b);
    }

    @Override
    public String getAnswer() {
        return "" + getValue();
    }

    private int getValue() {
        switch (op) {
            case Add:
                return a + b;
            case Sub:
                return a - b;
            case Mult:
                return a * b;
            case Div:
                return a / b;
        }

        throw new UnsupportedOperationException();
    }


    public enum Operation {
        Add('+'), Sub('-'), Mult('*'), Div('/');

        private final char sign;

        Operation(char sign) {
            this.sign = sign;
        }

        public char getSign() {
            return sign;
        }
    }

    public static ArithmeticQuestion generateQuestion(Random rand) {
        Operation op = Operation.values()[rand.nextInt(Operation.values().length)];
        int a = 0, b = 0;
        switch (op) {
            case Add:
                a = 10 + rand.nextInt(40);
                b = 10 + rand.nextInt(40);
                break;
            case Sub:
                a = 10 + rand.nextInt(40);
                b = 10 + rand.nextInt(a - 10);
                break;
            case Mult:
                a = 3 + rand.nextInt(17);
                b = 5 + rand.nextInt(5);
                break;
            case Div:
                b = 2 + rand.nextInt(8);
                a = b * (2 + rand.nextInt(8));
                break;
        }

        return new ArithmeticQuestion(op, a, b);
    }

}
