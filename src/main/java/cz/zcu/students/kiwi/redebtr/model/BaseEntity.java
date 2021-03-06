package cz.zcu.students.kiwi.redebtr.model;

import cz.zcu.students.kiwi.libs.domain.ValidationException;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
@MappedSuperclass
public class BaseEntity {

    private Long id;

    /**
     * @return true if the entity hasn't been persisted yet
     */
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public void validate() throws ValidationException {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
