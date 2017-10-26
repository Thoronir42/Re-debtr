package cz.zcu.students.kiwi.redebtr.model;

import cz.zcu.students.kiwi.comments.CommentThread;
import cz.zcu.students.kiwi.libs.domain.BaseObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "redebtr__post")
public class Post extends BaseObject {

    private UserProfile author;

    private Date dateCreated;

    private String message;

    private Type type;

    private CommentThread comments;

    public Post() {
    }

    public Post(UserProfile author, String message) {
        this.author = author;
        this.message = message;
        this.type = Type.TextPost;
        this.dateCreated = new Date();
    }

    @OneToOne
    public UserProfile getAuthor() {
        return author;
    }

    public Post setAuthor(UserProfile author) {
        this.author = author;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Post setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Column
    public String getMessage() {
        return message;
    }

    public Post setMessage(String message) {
        this.message = message;
        return this;
    }

    @Enumerated(EnumType.ORDINAL)
    public Type getType() {
        return type;
    }

    public Post setType(Type type) {
        this.type = type;
        return this;
    }

    @Transient
    public CommentThread getComments() {
        return comments;
    }

    public Post setComments(CommentThread comments) {
        this.comments = comments;
        return this;
    }

    public enum Type {
        TextPost, LinkPost, DebtPost
    }
}
