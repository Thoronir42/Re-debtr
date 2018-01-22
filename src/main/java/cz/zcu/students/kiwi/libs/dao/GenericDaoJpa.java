package cz.zcu.students.kiwi.libs.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.libs.domain.ValidationException;

/**
 * JPA implementation of the {@link GenericDao} interface.
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
public class GenericDaoJpa<T extends BaseEntity> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;
    private Class<T> persistedType;

    /**
     * @param persistedType type of the entity persisted by this DAO
     */
    public GenericDaoJpa(Class<T> persistedType) {
        this.persistedType = persistedType;
    }

    @Override
    public T save(T value) {
        if (value.isNew()) {
            em.persist(value);
            return value;
        } else {
            return em.merge(value);
        }
    }

    public T save(T value, boolean validate) throws ValidationException {
        if (validate) {
            value.validate();
        }

        return this.save(value);
    }

    @Override
    public T findOne(Long id) {
        return em.find(persistedType, id);
    }

    @Override
    public void remove(T toRemove) {
        if (!toRemove.isNew()) {
            em.remove(toRemove);
        }
    }
}
