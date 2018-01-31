package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.redebtr.persistence.GenericDaoJpa;
import org.springframework.stereotype.Service;

@Service
public class CommentThreadDaoJpa extends GenericDaoJpa<CommentThread> implements CommentThreadDao {
    public CommentThreadDaoJpa() {
        super(CommentThread.class);
    }

}
