package cz.zcu.students.kiwi.redebtr.helpers;

import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.auth.AuthorizationService;
import cz.zcu.students.kiwi.libs.security.IUser;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

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

    public AuthUser getAuthUser(User user) {
        return new AuthUser(user, authorizator);
    }

    public User getCurrentUser(HttpServletRequest req) {
        IUser user = this.authenticationService.getUser(req.getSession());

        return users.findByUsername(user.getIdentification());
    }

}
