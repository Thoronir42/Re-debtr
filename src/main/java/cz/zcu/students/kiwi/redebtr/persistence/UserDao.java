package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.libs.dao.GenericDao;
import cz.zcu.students.kiwi.redebtr.model.User;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public interface UserDao extends GenericDao<User> {

    /**
     *
     * @param username the requested username
     * @return user with the given username or null
     */
    User findByUsername(String username);

}
