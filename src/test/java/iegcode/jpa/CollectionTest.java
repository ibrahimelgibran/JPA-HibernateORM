package iegcode.jpa;

import iegcode.jpa.entity.Member;
import iegcode.jpa.entity.Name;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionTest {
    @Test
    void collection() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setFirst_name("Syaima");
        name.setMiddle_name("Al");
        name.setLast_name("Haura");

        Member member = new Member();
        member.setEmail("syaima@gmail.com");
        member.setName(name);

        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Masak");
        member.getHobbies().add("Main Hp");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();;
    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.getHobbies().add("Traveling");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();;
    }

    @Test
    void updateSkill() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 98);
        member.getSkills().put("PHP", 90);
        member.getSkills().put("Golang", 95);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();;
    }
}
