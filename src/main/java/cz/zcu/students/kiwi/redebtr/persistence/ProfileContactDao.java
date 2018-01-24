package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

public interface ProfileContactDao extends GenericDao<ProfileContact> {

    void addContact(UserProfile from, UserProfile to);

    boolean removeContact(UserProfile from, UserProfile to);
}
