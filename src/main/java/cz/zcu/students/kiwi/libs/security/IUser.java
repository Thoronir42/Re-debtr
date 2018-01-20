package cz.zcu.students.kiwi.libs.security;

import cz.zcu.students.kiwi.libs.auth.AclRole;

public interface IUser {

    String getIdentification();

    boolean isLoggedIn();

    AclRole[] getRoles();
}
