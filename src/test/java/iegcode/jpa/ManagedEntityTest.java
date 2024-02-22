package iegcode.jpa;

import iegcode.jpa.entity.Brand;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ManagedEntityTest {
    @Test
    void manageEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // unmanaged entity (biasa di run tapi tidak bisa ke upload ke DB sebelum di managed entity)
        Brand brand = new Brand();
        brand.setId("apple");
        brand.setName("Apple");
        entityManager.persist(brand); // managed entity

        brand.setName("Apple International");
//        entityManager.merge(brand);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manageEntityFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // managed entity
        Brand brand = entityManager.find(Brand.class, "apple");
        brand.setName("Apple Indonesia");

        entityTransaction.commit();
        entityManager.close();
    }
}
