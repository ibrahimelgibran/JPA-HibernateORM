package iegcode.jpa;

import iegcode.jpa.entity.*;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmbeddedTest {
    @Test
    void embedded() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirst_name("Syaima");
        name.setMiddle_name("Al");
        name.setLast_name("Haura");

        Member member = new Member();
        member.setEmail("syaima@gmail.com");
        member.setName(name);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();;
    }
    @Test
    void embeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("ieg");
        id.setDepartmentId("tech");

        Department department = new Department();
        department.setId(id);
        department.setName("TechnoCode");

        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();;
    }

    // jika primary keynya lebih dari 1 column bisa menggunakan embedded
    @Test // jika ingin melihat datanya
    void embeddedIdFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("ieg");
        id.setDepartmentId("tech");

        // maka where nya ada 2 yaitu company id dan department id saat di unit test
        Department department = entityManager.find(Department.class, id);
        Assertions.assertEquals("TechnoCode", department.getName());

        entityTransaction.commit();
        entityManager.close();;
    }
}

