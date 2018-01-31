package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
    public List<Post> prepareDashboard(UserProfile profile, List<UserProfile> contacts) {
        String inContacts = contacts.isEmpty() ? "" : " OR (p.author IN (:contacts) OR p.target IN (:contacts))";
        String tql = "SELECT p FROM Post p" +
                " JOIN p.author" +
                " LEFT JOIN p.comments" +
                " LEFT JOIN p.comments.comments" +
                " WHERE (p.author = :profile OR p.target = :profile)" +
                inContacts +
                " ORDER BY p.dateCreated DESC";

        TypedQuery<Post> query = this.em.createQuery(tql, Post.class);

        query.setParameter("profile", profile);
        if (!contacts.isEmpty()) {
            query.setParameter("contacts", contacts);
        }

        return query.getResultList();
    }

}
