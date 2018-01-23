package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserProfileDaoJpa extends GenericDaoJpa<UserProfile> implements UserProfileDao {
    public UserProfileDaoJpa() {
        super(UserProfile.class);
    }

    @Override
    public UserProfile findByUser(User user) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        CriteriaQuery<UserProfile> query = cb.createQuery(UserProfile.class);
        Root<UserProfile> up = query.from(UserProfile.class);
        query.where(cb.equal(up.join("user"), user));

        return getSingleOrNull(em.createQuery(query));
    }
}
