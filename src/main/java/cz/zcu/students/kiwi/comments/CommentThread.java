package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.libs.domain.BaseObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommentThread extends BaseObject implements Iterable<Comment>{
    public List<Comment> comments;

    public CommentThread() {
        this.comments = new ArrayList<>();
    }

    public CommentThread addComment(Comment c) {
        this.comments.add(c);

        return this;
    }

    @Override
    public Iterator<Comment> iterator() {
        return this.comments.iterator();
    }
}
