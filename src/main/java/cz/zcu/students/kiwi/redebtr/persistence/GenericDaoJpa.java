package cz.zcu.students.kiwi.redebtr.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import cz.zcu.students.kiwi.redebtr.model.BaseEntity;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

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
    public T save(T value) {
        EntityManager em = this.myEm;

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        if (value.isNew()) {
            em.persist(value);
            em.flush();

            transaction.commit();
            return value;
        } else {
            T n = em.merge(value);
            em.flush();

            transaction.commit();
            return n;
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
