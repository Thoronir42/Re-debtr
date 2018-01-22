package cz.zcu.students.kiwi.libs.manager;

import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.libs.domain.ValidationException;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public interface UserManager {

    /**
     * Method for authentication of user's credentials.
     *
     * @param username provided login
     * @param password provided password
     * @return true if username and password are a match, false otherwise
     */
    boolean authenticate(String username, String password);

    /**
     * Method for registering a new user.
     * @param newUser instance with new user data, expected not-null value
     * @throws ValidationException if the new user data instance is not in valid state,
     *                                 e.g. required fields are missing
     */
    void register(User newUser) throws ValidationException, RuntimeException;


}
