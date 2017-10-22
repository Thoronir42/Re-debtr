package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.libs.dao.GenericDaoJpa;
import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
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
        List<Post> posts = new ArrayList<Post>();
        UserProfile author = (new UserProfile()).setName("Doc.", "Scratch");
        IpsumGenerator ipsum = new IpsumGenerator();

        posts.add(new Post(author, ipsum.paragraphs(2)));
        posts.add(new Post(author, ipsum.paragraphs(2)));
        posts.add(new Post(author, ipsum.paragraphs(2)));
        posts.add(new Post(author, ipsum.paragraphs(2)));

        return posts;
    }

}
