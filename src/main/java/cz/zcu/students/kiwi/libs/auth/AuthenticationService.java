package cz.zcu.students.kiwi.libs.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cz.zcu.students.kiwi.libs.security.Encoder;
import cz.zcu.students.kiwi.libs.security.IUser;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private static final int
            STANDARD_SESSION_INTERVAL = 15 * 60, // 15 minutes
            PROLONGED_SESSION_INTERVAL = 14 * 60 * 60 * 24; // 2 weeks

    private static final String USER = "auth.user";

    @Autowired
    private UserDao userDao;
    @Autowired
    private Encoder encoder;

    /**
     * Signs in the user, if username and password match
     *
     * @param session  session associated with the request
     * @param username provided username
     * @param password provided password
     * @param remember
     * @return true if success, false otherwise
     */
    public boolean authenticate(HttpSession session, String username, String password, boolean remember) {
        User u = userDao.findByUsername(username);
        if (u == null || u.getStatus() == User.Status.Deleted) {
            encoder.fakeValidate();
            return false;
        }

        if (!encoder.validate(password, u.getPassword())) {
            return false;
        }

        session.setMaxInactiveInterval(remember ? PROLONGED_SESSION_INTERVAL : STANDARD_SESSION_INTERVAL);
        session.setAttribute(USER, username);
        return true;
    }

    public void clear(HttpSession session) {
        session.setAttribute(USER, null);
    }

    public String getUsername(HttpSession session) {
        return (String) session.getAttribute(USER);
    }

    /**
     * @param session session associated with the request
     * @return true if there is a user currently logged in within this session.
     */
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(USER) != null;
    }
}
