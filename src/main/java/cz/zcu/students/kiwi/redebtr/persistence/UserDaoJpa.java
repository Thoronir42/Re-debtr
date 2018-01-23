package cz.zcu.students.kiwi.redebtr.persistence;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import cz.zcu.students.kiwi.redebtr.model.User;
import org.springframework.stereotype.Repository;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
@Repository
public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {

    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        q.setParameter("username", username);

        return getSingleOrNull(q);
    }
}
