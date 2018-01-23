package cz.zcu.students.kiwi.libs.auth;


import cz.zcu.students.kiwi.libs.security.IUser;

public class AuthUser {

    private IUser user;
    private AuthorizationService authorization;

    public AuthUser(IUser user, AuthorizationService authorization) {
        this.user = user;
        this.authorization = authorization;
    }

    public boolean isAllowedTo(String resource) {
        return this.isAllowedTo(AclResource.valueOf(resource));
    }

    public boolean isAllowedTo(AclResource resource) {
        return this.authorization.isAllowed(this.user, resource);
    }

    public boolean isAllowedTo(String resource, String action) {
        return this.isAllowedTo(AclResource.valueOf(resource), AclAction.valueOf(action));
    }

    public boolean isAllowedTo(AclResource resource, AclAction action) {
        return this.authorization.isAllowed(this.user, resource, action);
    }

    public boolean isLoggedIn() {
        return this.user.isLoggedIn();
    }

    public IUser getUser() {
        return user;
    }
}
