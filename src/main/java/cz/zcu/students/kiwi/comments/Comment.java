package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

import java.util.Date;

public class Comment extends BaseEntity {

    protected CommentThread thread;

    protected UserProfile author;

    protected Date dateCreated;
    protected String text;

    public Comment() {
        this.dateCreated = new Date();
    }

    public Comment(CommentThread thread) {
        this.thread = thread;
    }

    public CommentThread getThread() {
        return thread;
    }

    public Comment setThread(CommentThread thread) {
        this.thread = thread;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Comment setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public UserProfile getAuthor() {
        return author;
    }

    public Comment setAuthor(UserProfile author) {
        this.author = author;
        return this;
    }
}
