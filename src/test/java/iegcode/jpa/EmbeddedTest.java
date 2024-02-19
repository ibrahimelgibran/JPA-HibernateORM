package iegcode.jpa;

import iegcode.jpa.entity.Customer;
import iegcode.jpa.entity.Member;
import iegcode.jpa.entity.Name;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
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
}
