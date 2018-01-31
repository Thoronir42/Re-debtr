package cz.zcu.students.kiwi.libs.auth;

import cz.zcu.students.kiwi.libs.security.IUser;
import junit.framework.TestCase;
import junit.framework.Assert.*;

public class AuthorizationServiceTest extends TestCase {

    public void testIsAllowed() {
        AuthorizationService as = new AuthorizationService() {
        };

        assertFalse(as.isAllowed(getUserWithRoles(), AclResource.Currency, AclAction.CREATE));
    }

    public void testAllow() {
        AuthorizationService as = new AuthorizationService() {
        };
        as.allow(AclRole.Admin, AclResource.UserProfile);

        IUser admin = getUserWithRoles(AclRole.Admin);

        assertTrue(as.isAllowed(admin, AclResource.UserProfile, AclAction.CREATE));
        assertTrue(as.isAllowed(admin, AclResource.UserProfile, AclAction.READ));
        assertTrue(as.isAllowed(admin, AclResource.UserProfile, AclAction.UPDATE));
        assertTrue(as.isAllowed(admin, AclResource.UserProfile, AclAction.DELETE));
        assertTrue(as.isAllowed(admin, AclResource.UserProfile, AclAction.ALL));
    }

    public void testAllowForbid() {
        AuthorizationService as = new AuthorizationService() {
        };

        as.allow(AclRole.User, AclResource.UserProfile);
        as.forbid(AclRole.User, AclResource.UserProfile, AclAction.DELETE);

        IUser user = getUserWithRoles(AclRole.User);

        assertTrue(as.isAllowed(user, AclResource.UserProfile, AclAction.CREATE));
        assertTrue(as.isAllowed(user, AclResource.UserProfile, AclAction.READ));
        assertTrue(as.isAllowed(user, AclResource.UserProfile, AclAction.UPDATE));
        assertFalse(as.isAllowed(user, AclResource.UserProfile, AclAction.DELETE));
        assertTrue(as.isAllowed(user, AclResource.UserProfile, AclAction.ALL));
    }

    private IUser getUserWithRoles(AclRole... roles) {
        return new IUser() {

            @Override
            public boolean isLoggedIn() {
                return false;
            }

            @Override
            public AclRole[] getRoles() {
                return roles;
            }
        };
    }
}
