package iegcode.jpa;

import iegcode.jpa.entity.Brand;
import iegcode.jpa.entity.Member;
import iegcode.jpa.entity.Product;
import iegcode.jpa.entity.User;
import iegcode.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void select() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery <Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands){
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void whereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where " +
                "m.name.first_name = :firstName and m.name.last_name = :lastName", Member.class);

        query.setParameter("firstName", "Syaima");
        query.setParameter("lastName", "Haura");

        List<Member> members = query.getResultList();
        for (Member member : members){
            System.out.println(member.getId() + " : " + member.getFullname());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void JoinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand", "Samsung");

        List<Product> products = query.getResultList();
        for (Product product : products){
            System.out.println(product.getId() + " : " + product.getName() + " : " + product.getBrand().getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void JoinFetchClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.likes p where p.name = :product", User.class);
        query.setParameter("product", "Samsung");

        List<User> users = query.getResultList();
        for (User user : users){
            System.out.println("User: " + user.getName());
            for (Product product : user.getLikes()){
                System.out.println("Product: " + product.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void orderClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc ", Brand.class);
        List<Brand> brands = query.getResultList();
        for (Brand brand : brands){
            System.out.println(brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }
}
