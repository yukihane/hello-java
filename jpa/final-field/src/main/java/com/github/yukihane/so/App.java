package com.github.yukihane.so;

import com.github.yukihane.so.entity.Branch;
import com.github.yukihane.so.entity.Company;

import javax.persistence.*;
import java.util.List;

public class App {

    public static void main(final String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myunit");


        insert(emf);
        select(emf);
    }

    private static void insert(final EntityManagerFactory emf) {

        final var c = new Company("company name");
        final var b1 = new Branch("b1");
        b1.setMembers(123);
        c.addBranch(b1);

        final EntityManager em = emf.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(c);
        tx.commit();
        em.close();
    }

    private static void select(final EntityManagerFactory emf) {
        final EntityManager em = emf.createEntityManager();
        final Query q = em.createQuery("select c from Company c");
        final List<Company> res = q.getResultList();
        res.forEach(c -> {
            System.out.println(c);
            c.getBranches().forEach(System.out::println);
        });
    }
}
