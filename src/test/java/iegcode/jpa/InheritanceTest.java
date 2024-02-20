package iegcode.jpa;

import iegcode.jpa.entity.Employee;
import iegcode.jpa.entity.Manager;
import iegcode.jpa.entity.User;
import iegcode.jpa.entity.VicePresident;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InheritanceTest {

    @Test
    void singleTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setId("berti");
        employee.setName("Odsan Berti");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("iegcode");
        manager.setName("Ibrahim Gibran");
        manager.setTotalEmployee(10);
        entityManager.persist(manager);

        VicePresident vicePresident = new VicePresident();
        vicePresident.setId("wahyu");
        vicePresident.setName("Wahyi Isyantina");
        vicePresident.setTotalManager(5);
        entityManager.persist(vicePresident);

        entityTransaction.commit();
        entityManager.close();
    }
    @Test
    void singleTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Manager manager = entityManager.find(Manager.class, "iegcode");
        Assertions.assertEquals("Ibrahim Gibran", manager.getName());

       Employee employee = entityManager.find(Employee.class, "wahyu");
       VicePresident vicePresident = (VicePresident) employee;
       Assertions.assertEquals("Wahyi Isyantina", vicePresident.getName());

        entityTransaction.commit();
        entityManager.close();
    }
}
