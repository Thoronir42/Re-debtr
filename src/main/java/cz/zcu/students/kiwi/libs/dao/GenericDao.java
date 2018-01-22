package cz.zcu.students.kiwi.libs.dao;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;

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

}
