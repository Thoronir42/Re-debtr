package cz.zcu.students.kiwi.libs.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
@MappedSuperclass
public class BaseObject {

    private Long id;

    /**
     * @return true if the entity hasn't been persisted yet
     */
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public void validate() throws ValidationException{
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
