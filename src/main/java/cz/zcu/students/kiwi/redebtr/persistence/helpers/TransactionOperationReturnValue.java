package cz.zcu.students.kiwi.redebtr.persistence.helpers;

import javax.persistence.EntityManager;

public interface TransactionOperationReturnValue<T> {
    T run(EntityManager em);
}
