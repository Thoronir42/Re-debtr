package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.temp.CommentGenerator;
import cz.zcu.students.kiwi.temp.IpsumGenerator;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDaoJpa extends GenericDaoJpa<Post> implements PostDao {

    public PostDaoJpa() {
        super(Post.class);
    }

    @Override
    public Post findByLocatorAndId(String locator, long id) {
        String tql = "Select p FROM Post p" +
                " WHERE p.id = :id AND p.target.locator = :locator";
        TypedQuery<Post> query = em.createQuery(tql, Post.class);

        query.setParameter("id", id).setParameter("locator", locator);

        return getSingleOrNull(query);
    }

    public List<Post> getPostOfProfile(UserProfile profile) {
        String tql = "SELECT p FROM Post p" +
                " JOIN p.author" +
                " WHERE p.target = :profile" +
                " ORDER BY p.dateCreated DESC";
        TypedQuery<Post> query = this.em.createQuery(tql, Post.class);

        query.setParameter("profile", profile);

        return query.getResultList();
    }

    @Override
    public List<Post> prepareDashboard(UserProfile profile) {
        if (true) throw new UnsupportedOperationException();
        CommentGenerator threadGen = new CommentGenerator();

        List<Post> posts = new ArrayList<Post>();
        UserProfile author = (new UserProfile().setLocator("Doc")).setName("Doc.", "Scratch");
        IpsumGenerator ipsum = new IpsumGenerator();

//        posts.add((new Post(author, ipsum.paragraphs(2)))
//                .setComments(threadGen.thread(2, 60)));
//        posts.add((new Post(author, ipsum.paragraphs(1)))
//                .setComments(threadGen.thread(0, 60)));
//        posts.add((new Post(author, ipsum.paragraphs(2)))
//                .setComments(threadGen.thread(4, 60)));

        return posts;
    }

}
