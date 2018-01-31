package cz.zcu.students.kiwi.comments;

import cz.zcu.students.kiwi.redebtr.persistence.GenericDao;

public interface CommentDao extends GenericDao<Comment>
{
    void addComment(ICommentThreadable wrapper, Comment comment);
}
