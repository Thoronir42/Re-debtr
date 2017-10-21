package cz.zcu.students.kiwi.libs.auth;

import org.springframework.stereotype.Service;

@Service
public class StaticAuthorizationService extends AuthorizationService {

    public StaticAuthorizationService() {
        this.allow(AclRole.User, AclResource.UserProfile);
        this.allow(AclRole.Admin, AclResource.UserProfile);
        this.allow(AclRole.Admin, AclResource.Currency);
    }
}
