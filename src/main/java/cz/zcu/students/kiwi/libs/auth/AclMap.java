package cz.zcu.students.kiwi.libs.auth;

import javafx.util.Pair;

import java.util.HashMap;

public class AclMap {

    private HashMap<AclMap.AclKey, Integer> map;

    AclMap() {
        this.map = new HashMap<>();
    }

    public void set(AclRole role, AclResource resource, AclAction aclAction) {
        AclKey key = new AclKey(role, resource);
        Integer val = this.map.getOrDefault(key, 0);

        val |= aclAction.getMask();

        this.map.put(key, val);
    }

    public boolean test(AclRole role, AclResource resource) {
        return this.test(role, resource, AclAction.ALL);
    }

    public boolean test(AclRole role, AclResource resource, AclAction action) {
        int val = this.map.getOrDefault(new AclKey(role, resource), 0);

        return (val & action.getMask()) == action.getMask();
    }

    class AclKey extends Pair<AclRole, AclResource> {
        AclKey(AclRole role, AclResource resource) {
            super(role, resource);
        }
    }
}
