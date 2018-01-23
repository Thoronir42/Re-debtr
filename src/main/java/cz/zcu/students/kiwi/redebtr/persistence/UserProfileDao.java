package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

public interface UserProfileDao extends GenericDao<UserProfile>{

    UserProfile findByUser(User user);
}
