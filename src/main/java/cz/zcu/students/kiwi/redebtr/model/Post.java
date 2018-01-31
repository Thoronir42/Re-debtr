package cz.zcu.students.kiwi.redebtr.model;

import cz.zcu.students.kiwi.comments.CommentThread;
import cz.zcu.students.kiwi.comments.ICommentThreadable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "redebtr__post")
public class Post extends BaseEntity implements ICommentThreadable {

    private UserProfile target;

    private UserProfile author;

    private Date dateCreated;

    private String message;

    private Type type;

    private CommentThread comments;

    public Post() {
    }

    public Post(UserProfile target,UserProfile author, String message) {
        this.target = target;
        this.author = author;
        this.message = message;
        this.type = Type.TextPost;
        this.dateCreated = new Date();
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public UserProfile getTarget() {
        return target;
    }

    public Post setTarget(UserProfile target) {
        this.target = target;
        return this;
    }

    @OneToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
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
    @Override
    public CommentThread getComments() {
        return comments;
    }

    @Override
    public void setComments(CommentThread comments) {
        this.comments = comments;
    }

    public enum Type {
        TextPost("text-post"), DebtPost("debt-post");

        private final String value;

        Type(String value) {

            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type byValue(String value) {
            for (Type t : Type.values()) {
                if (Objects.equals(t.value, value)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("No post type of " + value + " value found");
        }
    }
}
