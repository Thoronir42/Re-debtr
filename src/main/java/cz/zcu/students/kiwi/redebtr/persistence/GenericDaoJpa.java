package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.redebtr.persistence.helpers.TransactionOperation;
import cz.zcu.students.kiwi.redebtr.persistence.helpers.TransactionOperationReturnValue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

public class GenericDaoJpa<T extends BaseEntity> implements GenericDao<T> {

    //    @PersistenceContext
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
        // fixme: em = myEm
        this.em = this.myEm = emf.createEntityManager();
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
        return myEm.find(persistedType, id);
    }

    @Override
    public T create(T entity) {
        if (!entity.isNew()) {
            throw new IllegalStateException("Invalid create() call on non-new entity, use update() instead.");
        }

        return runTransaction((em) -> {
            em.persist(entity);
            return entity;
        });
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

        return runTransaction((EntityManager em) -> em.merge(entity));
    }

    public T update(T entity, boolean validate) throws ValidationException {
        if (validate) {
            entity.validate();
        }

        return this.update(entity);
    }

    @Override
    public void delete(T toRemove) {
        runTransaction((em) -> {
            if (!toRemove.isNew()) {
                em.remove(toRemove);
                em.flush();
            }
        });
    }

    protected <Q> Q getSingleOrNull(TypedQuery<Q> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    protected void runTransaction(TransactionOperation operation) {
        EntityTransaction transaction = myEm.getTransaction();
        try {
            transaction.begin();

            operation.run(myEm);
            myEm.flush();
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        }
    }

    protected <T> T runTransaction(TransactionOperationReturnValue<T> operation) {
        EntityTransaction transaction = myEm.getTransaction();
        try {
            transaction.begin();

            T ret = operation.run(myEm);
            myEm.flush();
            transaction.commit();

            return ret;
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        }
    }
}
