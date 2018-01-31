package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments__comment")
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

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    public CommentThread getThread() {
        return thread;
    }

    public Comment setThread(CommentThread thread) {
        this.thread = thread;
        return this;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public Comment setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Column
    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    public UserProfile getAuthor() {
        return author;
    }

    public Comment setAuthor(UserProfile author) {
        this.author = author;
        return this;
    }
}
