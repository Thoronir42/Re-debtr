package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;

@Service
public class ProfileContactDaoJpa extends GenericDaoJpa<ProfileContact> implements ProfileContactDao {

    public ProfileContactDaoJpa() {
        super(ProfileContact.class);
    }

    @Override
    public void addContact(UserProfile from, UserProfile to) {
        String tql = "SELECT pc FROM ProfileContact pc" +
                " WHERE (pc.initiator = :from AND pc.receiver = :to)" +
                " OR (pc.initiator = :to AND pc.receiver = :from)";
        TypedQuery<ProfileContact> query = this.em.createQuery(tql, ProfileContact.class);
        query.setParameter("from", from).setParameter("to", to);


        if (getSingleOrNull(query) != null) {
            throw new IllegalStateException("Contact alreaady exists");
        }

        ProfileContact contact = new ProfileContact(from, to);
        this.create(contact);
    }

    @Override
    public boolean removeContact(UserProfile from, UserProfile to) {
        String tql = "DELETE FROM ProfileContact pc" +
                " WHERE (pc.initiator = :from AND pc.receiver = :to)" +
                " OR (pc.initiator = :to AND pc.receiver = :from)";

        TypedQuery<ProfileContact> query = this.em.createQuery(tql, ProfileContact.class);

        return query.executeUpdate() > 0;
    }

}
