package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

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
        return findConnectionsByStatus(profile, null);
    }

    @Override
    public List<UserProfile> findConnectionsByStatus(UserProfile profile, ProfileContact.Status status) {
        String tql = "SELECT (case when pc.initiator = :profile then pc.receiver else pc.initiator end) as up, 1 " +
                " FROM ProfileContact pc" +
                " WHERE (pc.initiator = :profile OR pc.receiver = :profile)";
        if (status != null) {
            tql += " AND pc.status = :status";
        }
        TypedQuery<Object[]> query = this.em.createQuery(tql, Object[].class);

        query.setParameter("profile", profile);
        if (status != null) {
            query.setParameter("status", status);
        }

        return query.getResultList().stream()
                .map(o -> (UserProfile) o[0])
                .collect(Collectors.toList());
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

    public List<UserProfile> markProfileContacts(List<UserProfile> profiles, UserProfile target) {
        if (profiles.size() == 0) {
            return profiles;
        }

        String jpql2 = "SELECT (case when pc.initiator = :target then pc.receiver else pc.initiator end) AS up, pc.status," +
                " (pc.receiver = :target) as inverse \n" +
                " FROM ProfileContact pc \n" +
                " WHERE (pc.initiator = :target AND pc.receiver IN (:profiles)) \n" +
                "    OR (pc.initiator IN (:profiles) AND pc.receiver = :target)";

        TypedQuery<Object[]> query = this.em.createQuery(jpql2, Object[].class);

        query.setParameter("profiles", profiles);
        query.setParameter("target", target);

        List<Object[]> resultList = query.getResultList();

        resultList.forEach(result -> {
            UserProfile up = (UserProfile) result[0];
            ProfileContact.Status status = (ProfileContact.Status) result[1];
            boolean inverse = (Boolean) result[2];
            profiles.forEach(profile -> {
                if (profile.equals(up)) {
                    profile.setContactStatus(status == ProfileContact.Status.Requested && inverse ? ProfileContact.Status.Received : status);
                }
            });
        });

        return profiles;
    }

    @Override
    public ProfileContact findRelation(UserProfile profile, UserProfile profile1) {
        String tql = "SELECT pc FROM ProfileContact pc" +
                " WHERE (pc.initiator = :p1 AND pc.receiver = :p2)" +
                " OR    (pc.initiator = :p2 AND pc.receiver = :p1)";

        TypedQuery<ProfileContact> q = this.em.createQuery(tql, ProfileContact.class)
                .setParameter("p1", profile)
                .setParameter("p2", profile1);

        return getSingleOrNull(q);
    }

    @Override
    public ProfileContact.Status findRelationStatus(UserProfile profile, UserProfile profile1) {
        System.out.println(profile.getId());
        System.out.println(profile1.getId());
        ProfileContact relation = findRelation(profile, profile1);
        if (relation == null) {
            return null;
        }
        if (relation.getStatus() == ProfileContact.Status.Requested) {
            return profile.equals(relation.getInitiator()) ? ProfileContact.Status.Requested : ProfileContact.Status.Received;
        }

        return relation.getStatus();
    }
}
