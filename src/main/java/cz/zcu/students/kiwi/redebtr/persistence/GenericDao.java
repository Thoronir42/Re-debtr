package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

/**
 * Common interface for all DAOs
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public interface GenericDao<T extends BaseEntity> {

    T save(T value);

    T findOne(Long id);

    void remove(T toRemove);

    T create(T entity);
    T create(T entity, boolean validate) throws ValidationException;

    T update(T entity);
    T update(T entity, boolean validate) throws ValidationException;
}
