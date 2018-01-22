package cz.zcu.students.kiwi.redebtr.model;

import javax.persistence.*;

import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.security.Encoder;
import org.apache.commons.lang3.StringUtils;

/**
 * Entity representing application User.
 * <p>
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
@Entity
@Table(name = "identity__user")
public class User extends BaseEntity {

    public static Encoder encoder;

    private String username;

    private String email;

    private Status status;

    private String password;

    private UserProfile profile;

    protected String confirmationCode;

    public User() {
    }

    public User(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);

        this.status = Status.Unverified;
    }

    /*
    ########### API ##################
     */

    public void validate() throws ValidationException {
        if (StringUtils.isBlank(username)) throw new ValidationException("Username is a required field");
        if (StringUtils.isBlank(password)) throw new ValidationException("Password is a required field");
    }

    /*
    ########### MAPPINGS #####################
     */
    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println(password);
        this.password = encoder.encode(password);

        System.out.println(this.password);
    }

    @Column
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @OneToOne
    @JoinColumn
    public UserProfile getProfile() {
        return profile;
    }

    public User setProfile(UserProfile profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(username != null ? !username.equals(user.username) : user.username != null);

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' +
                '}';
    }

    public enum Status {
        Unverified, Active, Deleted;
    }
}
