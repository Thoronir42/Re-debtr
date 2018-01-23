package cz.zcu.students.kiwi.redebtr.persistence;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDaoJpa<T extends BaseEntity> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    protected EntityManager myEm;
    private Class<T> persistedType;

    /**
     * @param persistedType type of the entity persisted by this DAO
     */
    public GenericDaoJpa(Class<T> persistedType) {
        this.persistedType = persistedType;
    }

    @Autowired
    public void initEM(EntityManagerFactory emf) {
        this.myEm = emf.createEntityManager();
    }

    @Override
    public T save(T entity) {
        if (entity.isNew()) {
            return this.create(entity);
        } else {
            return this.update(entity);
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
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        if (!toRemove.isNew()) {
            em.remove(toRemove);
        }

        transaction.commit();

    }

    @Override
    public T create(T entity) {
        if (!entity.isNew()) {
            throw new IllegalStateException("Invalid create() call on non-new entity, use update() instead.");
        }
        EntityManager em = this.myEm;

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(entity);
        em.flush();

        transaction.commit();
        return entity;
    }

    public T create(T entity, boolean validate) throws ValidationException {
        if (validate) {
            entity.validate();
        }

        return this.create(entity);
    }

    @Override
    public T update(T entity) {
        if (entity.isNew()) {
            throw new IllegalStateException("Invalid update() call on non-new entity, use create() instead.");
        }

        EntityManager em = this.myEm;

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        T n = em.merge(entity);
        em.flush();

        transaction.commit();
        return n;
    }

    public T update(T entity, boolean validate) throws ValidationException {
        if (validate) {
            entity.validate();
        }

        return this.update(entity);
    }

    protected T getSingleOrNull(TypedQuery<T> q) {
        try {
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
