package iegcode.jpa;

import iegcode.jpa.entity.Credential;
import iegcode.jpa.entity.Customer;
import iegcode.jpa.entity.User;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityRelationshipTest {
    @Test
    void oneToOnePersist() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Credential credential = new Credential();
        credential.setId("Gibran");
        credential.setEmail("gibran@example.com");
        credential.setPassword("rahasia");
        entityManager.persist(credential);

        User user = new User();
        user.setId("Gibran");
        user.setName("ElGibran");
        entityManager.persist(user);

        entityTransaction.commit();
        entityManager.close();
    }
    @Test
    void oneToOneFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "Gibran");
        Assertions.assertNotNull(user.getCredential());

        Assertions.assertEquals("gibran@example.com", user.getCredential().getEmail());
        Assertions.assertEquals("rahasia", user.getCredential().getPassword());

        entityTransaction.commit();
        entityManager.close();
    }
}
