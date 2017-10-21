package cz.zcu.students.kiwi.libs.auth;

import cz.zcu.students.kiwi.libs.security.IUser;

public interface IAuthorizationService {
    boolean isAllowed(IUser user, AclResource resource);

    boolean isAllowed(IUser user, AclResource resource, AclAction action);
}
