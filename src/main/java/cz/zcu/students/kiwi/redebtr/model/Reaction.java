package cz.zcu.students.kiwi.redebtr.model;

public class Reaction {
    private final String name;
    private final String cssClass;
    private final String caption;

    public Reaction(String name, String cssClass, String caption) {
        this.name = name;
        this.cssClass = cssClass;
        this.caption = caption;
    }

    public String getName() {
        return name;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getCaption() {
        return caption;
    }
}
