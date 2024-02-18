package iegcode.jpa;

import iegcode.jpa.entity.Customer;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CrudTest {
    
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    // menyisipkan
    @Test
    void insert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Gibran");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    // menemukan kesatuan find entity
    @Test
    void find() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer =entityManager.find(Customer.class, "1");
        Assertions.assertEquals("1", customer.getId());
        Assertions.assertEquals("Gibran", customer.getName());

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    // UPDATE
    @Test
    void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "1");
        customer.setName("Ibrahim Gibran");
        customer.setAge((byte)19);
        customer.setMarried(false);

        entityManager.merge(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    // delete
    @Test
    void remove() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer =entityManager.find(Customer.class, "1");
        entityManager.remove(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
