package cz.zcu.students.kiwi.libs.auth;

import javax.servlet.http.HttpSession;

import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.libs.security.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Permission;

@Service
public class AuthenticationService {

    private static final String USER = "auth.user";

    private UserManager userManager;

    @Autowired
    public AuthenticationService(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Signs in the user, if username and password match
     *
     * @param session  session associated with the request
     * @param username provided username
     * @param password provided password
     * @return true if success, false otherwise
     */
    public boolean authenticate(HttpSession session, String username, String password) {
        boolean authenticated = userManager.authenticate(username, password);

        if (authenticated) {
            session.setAttribute(USER, username);

            return true;
        }

        return false;
    }

    public IUser getUser() {
        return new IUser() {
            @Override
            public boolean isLoggedIn() {
                return (System.currentTimeMillis() % 2) == 0;
            }

            @Override
            public AclRole[] getRoles() {
                return new AclRole[0];
            }
        };
    }

    /**
     * @param session session associated with the request
     * @return true if there is a user currently logged in within this session.
     */
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(USER) != null;
    }
}
