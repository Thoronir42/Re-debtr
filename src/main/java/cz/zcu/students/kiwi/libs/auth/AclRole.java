package cz.zcu.students.kiwi.libs.auth;

public enum AclRole {
    Guest, User, Admin;

    public String dbValue() {
        return this.name().toLowerCase();
    }
}
