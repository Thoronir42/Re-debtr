package cz.zcu.students.kiwi.redebtr.model;

import cz.zcu.students.kiwi.libs.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "identity__profile")
public class UserProfile extends BaseEntity {


    private User user;

    private String firstName;
    private String lastName;

    private String locator;

    private List<UserProfile> connections;

    public UserProfile() {
        this("");
    }

    public UserProfile(String locator) {
        this.connections = new ArrayList<>();
        this.setLocator(locator);
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

    @Column(unique = true)
    public String getLocator() {
        return locator;
    }

    public UserProfile setLocator(String locator) {
        this.locator = locator;
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
