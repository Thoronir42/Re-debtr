package cz.zcu.students.kiwi.libs.auth;

import cz.zcu.students.kiwi.libs.security.IUser;
import org.springframework.stereotype.Service;

public abstract class AuthorizationService implements IAuthorizationService {
    private final AclMap forbidden;
    private final AclMap allowed;

    public AuthorizationService() {
        this.allowed = new AclMap();
        this.forbidden = new AclMap();
    }

    void allow(AclRole role, AclResource resource) {
        this.allow(role, resource, AclAction.ALL);
    }

    void allow(AclRole role, AclResource resource, AclAction ...actions) {
        for (AclAction a : actions) {
            this.allowed.set(role, resource, a);
        }

    }

    void forbid(AclRole role, AclResource resource) {
        this.forbid(role, resource, AclAction.ALL);
    }

    void forbid(AclRole role, AclResource resource, AclAction ...actions) {
        for(AclAction a : actions) {
            this.forbidden.set(role, resource, a);
        }

    }


    @Override
    public boolean isAllowed(IUser user, AclResource resource) {
        return this.isAllowed(user, resource, AclAction.ALL);
    }

    @Override
    public boolean isAllowed(IUser user, AclResource resource, AclAction action) {
        for (AclRole role : user.getRoles()) {
            if (this.forbidden.test(role, resource, action)) {
                return false;
            }
        }

        for (AclRole role : user.getRoles()) {
            if (role == AclRole.Admin) {
                return true;
            }
            if (this.allowed.test(role, resource, action)) {
                return true;
            }
        }

        return false;
    }
}
