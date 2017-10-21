package cz.zcu.students.kiwi.libs.auth;

public enum AclAction {
    CREATE(0x01), READ(0x02), UPDATE(0x04), DELETE(0x08), ALL(0xFF);

    private int mask;

    private AclAction(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return this.mask;
    }
}
