package iegcode.jpa;

import iegcode.jpa.entity.Customer;
import iegcode.jpa.entity.CustomerType;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class EnumTest {
    @Test
    void enumTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("3");
        customer.setName("Syakuro");
        customer.setPrimaryEmail("syakuro@exemple.com");
        customer.setAge((byte) 5);
        customer.setMarried(true);
        customer.setType(CustomerType.PREMIUM);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();;
    }
}
