package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "comments__thread")
public class CommentThread extends BaseEntity implements Iterable<Comment>{
    public List<Comment> comments;

    public CommentThread() {
        this.comments = new ArrayList<>();
    }

    public CommentThread addComment(Comment c) {
        this.comments.add(c);
        c.setThread(this);

        return this;
    }

    @OneToMany(mappedBy = "thread")
    public List<Comment> getComments() {
        return comments;
    }

    public CommentThread setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public Iterator<Comment> iterator() {
        return this.comments.iterator();
    }
}
