package iegcode.jpa;

import iegcode.jpa.entity.Category;
import iegcode.jpa.entity.Customer;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DateTest {
    @Test
    void column() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Food");
        category.setDescription("Makanan Terenak");
        category.setCreateAt(Calendar.getInstance());
        category.setUpdateAt(LocalDateTime.now());

        entityManager.persist(category);

        entityTransaction.commit();
        entityManager.close();;
    }
}
