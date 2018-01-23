package cz.zcu.students.kiwi.redebtr.model;

import javax.persistence.*;

@Entity
@Table(name = "identity__profile")
public class UserProfile extends BaseEntity {


    private User user;


    private String firstName;
    private String lastName;

    private String locator;

    private String avatar;


    public UserProfile() {
    }

    public UserProfile(User user) {
        this.setUser(user);
    }

    /*
    ########### API ##################
     */

    /*
    ########### MAPPINGS #####################
     */

    @OneToOne
    @JoinColumn
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
    public String getFullName() {
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

    @Column
    public String getAvatar() {
        return avatar;
    }

    public UserProfile setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
