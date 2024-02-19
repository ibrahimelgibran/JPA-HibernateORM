package iegcode.jpa;

import iegcode.jpa.entity.Credential;
import iegcode.jpa.entity.Customer;
import iegcode.jpa.entity.User;
import iegcode.jpa.entity.Wallet;
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
        Assertions.assertNotNull(user.getWallet());

        Assertions.assertEquals("gibran@example.com", user.getCredential().getEmail());
        Assertions.assertEquals("rahasia", user.getCredential().getPassword());
        Assertions.assertEquals(1_000_000, user.getWallet().getBalance());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneJoinColumn() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "Gibran");

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(1_000_000L);
        entityManager.persist(wallet);

        entityTransaction.commit();
        entityManager.close();
    }
}
