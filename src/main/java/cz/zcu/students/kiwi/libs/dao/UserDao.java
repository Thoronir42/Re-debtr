package cz.zcu.students.kiwi.libs.dao;

import cz.zcu.students.kiwi.libs.domain.User;

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
