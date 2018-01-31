package cz.zcu.students.kiwi.redebtr.persistence.helpers;

import javax.persistence.EntityManager;

public interface TransactionOperation {
    void run(EntityManager em);
}
