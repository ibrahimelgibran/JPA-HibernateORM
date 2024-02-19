package iegcode.jpa;

import iegcode.jpa.entity.Customer;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    void column() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Gibran");
        customer.setPrimaryEmail("elgibran@exemple.com");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();;
    }

    @Test
    void Transient() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("4");
        customer.setName("Gibran");
        customer.setPrimaryEmail("elgibran@exemple.com");
        customer.setFullName("Ibrahim El Gibran");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();;
    }
}

