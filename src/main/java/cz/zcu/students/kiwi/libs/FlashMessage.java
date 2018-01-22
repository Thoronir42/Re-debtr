package cz.zcu.students.kiwi.libs;

public class FlashMessage {

    private final String text;
    private final Level level;

    protected FlashMessage(String text, Level level) {

        this.text = text;
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public String getBsLevel() {
        return level.getBs();
    }

    public Level getLevel() {
        return level;
    }

    public static FlashMessage Info(String message) {
        return new FlashMessage(message, Level.Info);
    }

    public static FlashMessage Success(String message) {
        return new FlashMessage(message, Level.Success);
    }

    public static FlashMessage Error(String message) {
        return new FlashMessage(message, Level.Error);
    }


    enum Level {
        Info("info"), Error("danger"), Success("success");

        private final String bsLevel;

        Level(String bsLevel) {
            this.bsLevel = bsLevel;
        }

        public String getBs() {
            return this.bsLevel;
        }
    }
}
