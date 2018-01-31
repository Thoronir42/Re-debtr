package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

import java.util.List;

public interface UserProfileDao extends GenericDao<UserProfile> {

    UserProfile findByUser(User user);

    List<UserProfile> findConnectionsOf(UserProfile profile);

    List<UserProfile> findConnectionsByStatus(UserProfile profile, ProfileContact.Status status);

    List<UserProfile> searchEverywhere(String search);

    UserProfile findByLocator(String locator);

    List<UserProfile> markProfileContacts(List<UserProfile> profiles, UserProfile target);

    ProfileContact findRelation(UserProfile profile, UserProfile profile1);

    ProfileContact.Status findRelationStatus(UserProfile profile, UserProfile profile1);
}
