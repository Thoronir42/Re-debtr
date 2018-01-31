package cz.zcu.students.kiwi.redebtr.helpers;

import cz.zcu.students.kiwi.libs.auth.AclRole;
import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.auth.AuthorizationService;
import cz.zcu.students.kiwi.libs.security.IUser;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthHelper {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthorizationService authorizator;
    @Autowired
    private UserDao users;

    public AuthUser getAuthUser(HttpServletRequest req) {
        return getAuthUser(getCurrentUser(req));
    }

    public AuthUser getAuthUser(IUser user) {
        if (user == null) {
            user = new GuestUser();
        }

        return new AuthUser(user, authorizator);
    }

    public User getCurrentUser(HttpServletRequest req) {
        String username = this.authenticationService.getUsername(req.getSession());
        if (username == null) {
            return null;
        }

        return users.findByUsername(username);
    }


    static class GuestUser implements IUser {
        @Override
        public boolean isLoggedIn() {
            return false;
        }

        @Override
        public AclRole[] getRoles() {
            return new AclRole[]{AclRole.Guest};
        }
    }
}
