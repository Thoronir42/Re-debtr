package cz.zcu.students.kiwi.libs.security;

import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashTest extends TestCase {

    public void testCreateAccept() throws InvalidKeySpecException, NoSuchAlgorithmException {
        for(int i = 0; i < 100; i++)
        {
            String password = ""+i;
            String hash = PasswordHash.createHash(password);
            String secondHash = PasswordHash.createHash(password);
            assertFalse("TWO HASHES ARE EQUAL!", hash.equals(secondHash));

            String wrongPassword = ""+(i+1);
            boolean accept = PasswordHash.validatePassword(wrongPassword, hash);
            assertFalse("WRONG PASSWORD ACCEPTED!", accept);

            accept = PasswordHash.validatePassword(password, hash);
            assertTrue("GOOD PASSWORD NOT ACCEPTED!", accept);
        }
    }

    public void createHashes() throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Print out 10 hashes
        for(int i = 0; i < 10; i++)
            System.out.println(PasswordHash.createHash("p\r\nassw0Rd!"));
    }
}
