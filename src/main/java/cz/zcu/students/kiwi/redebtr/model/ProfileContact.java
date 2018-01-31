package cz.zcu.students.kiwi.redebtr.model;

import cz.zcu.students.kiwi.libs.domain.ValidationException;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "redebtr__profile_contact")
public final class ProfileContact extends BaseEntity {

    protected UserProfile initiator;

    protected UserProfile receiver;

    protected Date dateCreated;

    protected Date dateResolved;

    protected Status status;

    public ProfileContact() {
        this.status = Status.Requested;
        this.dateCreated = new Date();
    }

    public ProfileContact(UserProfile initiator, UserProfile receiver) {
        this();

        this.initiator = initiator;
        this.receiver = receiver;
    }

    @Override
    public void validate() throws ValidationException {
        if (initiator == null || receiver == null) {
            throw new ValidationException("Contact be two sided");
        }

        if (this.initiator == this.receiver) {
            throw new ValidationException("Contact must be between different profiles");
        }
    }

    @ManyToOne
    @JoinColumn
    public UserProfile getInitiator() {
        return initiator;
    }

    public ProfileContact setInitiator(UserProfile initiator) {
        this.initiator = initiator;
        return this;
    }

    @ManyToOne
    @JoinColumn
    public UserProfile getReceiver() {
        return receiver;
    }

    public ProfileContact setReceiver(UserProfile receiver) {
        this.receiver = receiver;
        return this;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public ProfileContact setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateResolved() {
        return dateResolved;
    }

    public ProfileContact setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
        return this;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public ProfileContact setStatus(Status status) {
        this.status = status;
        return this;
    }

    public enum Status {
        Requested,
        Received,
        Accepted,
        Declined,
    }

}
