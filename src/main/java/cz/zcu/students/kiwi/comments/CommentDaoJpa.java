package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.redebtr.persistence.GenericDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDaoJpa extends GenericDaoJpa<Comment> implements CommentDao {

    @Autowired
    CommentThreadDao threads;

    public CommentDaoJpa() {
        super(Comment.class);
    }

    @Override
    public void addComment(ICommentThreadable wrapper, Comment comment) {
        if(wrapper.getComments() == null) {
            CommentThread thread = new CommentThread();
            wrapper.setComments(thread);
            threads.create(thread);
        }

        wrapper.getComments().addComment(comment);
        this.save(comment);
    }
}
