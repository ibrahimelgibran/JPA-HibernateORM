package iegcode.jpa;

import iegcode.jpa.entity.*;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

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

    @Test
    void oneToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("samsung");
        brand.setName("Samsung");
        entityManager.persist(brand);

        Product product1 = new Product();
        product1.setId("p1");
        product1.setName("Samsung Galaxy 1");
        product1.setBrand(brand);
        product1.setPrice(1_000_000L);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setId("p2");
        product2.setName("Samsung Galaxy 2");
        product2.setBrand(brand);
        product2.setPrice(2_000_000L);
        entityManager.persist(product2);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToManyFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "samsung");
        Assertions.assertNotNull(brand.getProducts());
        Assertions.assertEquals(2, brand.getProducts().size());

        brand.getProducts().forEach(product -> System.out.println(product.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manyToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "Gibran");
        user.setLikes(new HashSet<>());

        Product product1 = entityManager.find(Product.class, "p1");
        Product product2 = entityManager.find(Product.class, "p1");

        user.getLikes().add(product1);
        user.getLikes().add(product2);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manyToManyUpdate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "Gibran");
        Product product = null;
        for (Product item : user.getLikes()){
            product = item;
            break;
        }

        user.getLikes().remove(product);
        entityManager.merge(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void fetch() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

       Product product =entityManager.find(Product.class, "p1");
       Brand brand = product.getBrand();
       brand.getName();
       Assertions.assertNotNull(brand)  ;

//        Brand brand = entityManager.find(Brand.class, "samsung");

        entityTransaction.commit();
        entityManager.close();
    }
}
