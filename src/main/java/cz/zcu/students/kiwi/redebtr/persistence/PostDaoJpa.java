package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.temp.CommentGenerator;
import cz.zcu.students.kiwi.temp.IpsumGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoJpa extends GenericDaoJpa<Post> implements PostDao {

    public PostDaoJpa() {
        super(Post.class);
    }

    @Override
    public List<Post> prepareDashboard(UserProfile profile) {
        CommentGenerator threadGen = new CommentGenerator();

        List<Post> posts = new ArrayList<Post>();
        UserProfile author = (new UserProfile().setLocator("Doc")).setName("Doc.", "Scratch");
        IpsumGenerator ipsum = new IpsumGenerator();

        posts.add((new Post(author, ipsum.paragraphs(2)))
                .setComments(threadGen.thread(2, 60)));
        posts.add((new Post(author, ipsum.paragraphs(1)))
                .setComments(threadGen.thread(0, 60)));
        posts.add((new Post(author, ipsum.paragraphs(2)))
                .setComments(threadGen.thread(4, 60)));

        return posts;
    }

}
