package cz.zcu.students.kiwi.redebtr.model;

import cz.zcu.students.kiwi.libs.domain.BaseObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "identity__profile")
public class UserProfile extends BaseObject {


    private User user;

    /**
     * Login, unique
     */
    private String firstName;
    /**
     * Secret for signing-in
     */
    private String lastName;

    private List<UserProfile> connections;

    public UserProfile() {
        this.connections = new ArrayList<>();
    }

    /*
    ########### API ##################
     */

    /*
    ########### MAPPINGS #####################
     */

    @OneToOne(mappedBy = "profile")
    public User getUser() {
        return user;
    }

    public UserProfile setUser(User user) {
        this.user = user;
        return this;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    public UserProfile setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public UserProfile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Transient
    public String getName() {
        return this.firstName + " " + this.getLastName();
    }

    public UserProfile setName(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        return this;
    }

    @Transient
    @ManyToMany(mappedBy = "connections")
    public List<UserProfile> getConnections() {
        return connections;
    }

    public UserProfile setConnections(List<UserProfile> friends) {
        this.connections = friends;
        return this;
    }
}
