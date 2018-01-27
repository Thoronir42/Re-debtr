package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public List<UserProfile> findConnectionsOf(UserProfile profile) {
        String tql = "SELECT pc FROM ProfileContact pc" +
                " JOIN pc.initiator" +
                " JOIN pc.receiver" +
                " WHERE pc.initiator = :profile OR pc.receiver = :profile";
        TypedQuery<ProfileContact> query = this.em.createQuery(tql, ProfileContact.class);

        query.setParameter("profile", profile);

        List<ProfileContact> contacts = query.getResultList();

        return contacts.stream().map(pc -> {
            System.out.println(pc.getInitiator().getFullName() + " => " + pc.getReceiver().getFullName());

            if (profile.equals(pc.getInitiator())) {
                return pc.getReceiver();
            }
            if (profile.equals(pc.getReceiver())) {
                return pc.getInitiator();
            }
            System.err.println("Retrieved invalid contact");
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserProfile> searchEverywhere(String search) {
        String tql = "SELECT up FROM UserProfile up WHERE ( up.firstName LIKE :q OR up.lastName LIKE :q OR up.locator LIKE :q )";
        TypedQuery<UserProfile> query = this.em.createQuery(tql, UserProfile.class);

        query.setParameter("q", "%" + search + "%");

        return query.getResultList();
    }

    @Override
    public UserProfile findByLocator(String locator) {
        String tql = "SELECT up FROM UserProfile up WHERE up.locator = :locator";
        TypedQuery<UserProfile> query = this.em.createQuery(tql, UserProfile.class);

        query.setParameter("locator", locator);

        return this.getSingleOrNull(query);
    }
}
