package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.comments.CommentThread;
import cz.zcu.students.kiwi.libs.dao.GenericDaoJpa;
import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.temp.CommentGenerator;
import cz.zcu.students.kiwi.temp.IpsumGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoJpa extends GenericDaoJpa<Post> {

    public PostDaoJpa() {
        super(Post.class);
    }

    public List<Post> findPost() {
        CommentGenerator threadGen = new CommentGenerator();

        List<Post> posts = new ArrayList<Post>();
        UserProfile author = (new UserProfile()).setName("Doc.", "Scratch");
        IpsumGenerator ipsum = new IpsumGenerator();

        CommentThread thread = threadGen.thread(2, 60);

        posts.add((new Post(author, ipsum.paragraphs(2))).setComments(thread));
        posts.add((new Post(author, ipsum.paragraphs(2))).setComments(thread));
        posts.add((new Post(author, ipsum.paragraphs(2))).setComments(thread));
        posts.add((new Post(author, ipsum.paragraphs(2))).setComments(thread));

        return posts;
    }

}
