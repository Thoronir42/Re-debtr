package cz.zcu.students.kiwi.redebtr.model;

import javax.persistence.*;

import cz.zcu.students.kiwi.libs.domain.BaseObject;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
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
public class User extends BaseObject {
    /**
     * Login, unique
     */
    private String username;


    /**
     * Secret for signing-in
     */

    private String password;

    private UserProfile profile;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
        this.password = password;
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
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
