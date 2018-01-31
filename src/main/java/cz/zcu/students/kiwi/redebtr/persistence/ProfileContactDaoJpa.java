package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;

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


        ProfileContact contact = getSingleOrNull(query);
        if (contact != null) {
            System.out.println("Found contact: " + contact.getStatus());
            if (contact.getStatus() == ProfileContact.Status.Accepted) {
                throw new IllegalStateException("Contact alreaady exists");
            }

            if (contact.getReceiver().equals(from)) {
                contact.setStatus(ProfileContact.Status.Accepted);
                contact.setDateResolved(new Date());
                this.update(contact);
            } else {
                System.err.println("Contact request already created");
            }

            return;
        }

        contact = new ProfileContact(from, to);
        this.create(contact);
    }

    @Override
    public boolean removeContact(UserProfile from, UserProfile to) {
        String tql = "DELETE FROM ProfileContact pc" +
                " WHERE (pc.initiator = :from AND pc.receiver = :to)" +
                " OR (pc.initiator = :to AND pc.receiver = :from)";

        Integer deleted = runTransaction(em -> {
            Query query = this.myEm.createQuery(tql);
            query.setParameter("from", from).setParameter("to", to);

            return query.executeUpdate();
        });

        return deleted > 0;
    }

}
