package cz.zcu.students.kiwi.redebtr.model;

import javax.persistence.*;

import cz.zcu.students.kiwi.libs.auth.AclRole;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.security.Encoder;
import cz.zcu.students.kiwi.libs.security.IUser;
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
public class User extends BaseEntity implements IUser{

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
        this.setPassword(password, true);

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
    @Enumerated
    public Status getStatus() {
        return status;
    }

    public User setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Column
    public String getConfirmationCode() {
        return confirmationCode;
    }

    public User setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
        return this;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword(String password, boolean hash) {
        if(hash) {
            if(encoder != null) {
                password = encoder.encode(password);
            } else {
                System.err.println("Failed to set password, encoder not present");
            }
        }

        this.setPassword(password);
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

    @Override
    @Transient
    public String getIdentification() {
        return getUsername();
    }

    @Override
    @Transient
    public boolean isLoggedIn() {
        return getUsername() != null;
    }

    @Override
    @Transient
    public AclRole[] getRoles() {
        return new AclRole[] {AclRole.User};
    }

    public enum Status {
        Unverified, Active, Deleted;
    }
}
