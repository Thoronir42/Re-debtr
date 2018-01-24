package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

import java.util.List;

public interface UserProfileDao extends GenericDao<UserProfile>{

    UserProfile findByUser(User user);

    List<UserProfile> findConnectionsOf(UserProfile profile);

    List<UserProfile> searchEverywhere(String search);

    UserProfile findByLocator(String locator);
}
