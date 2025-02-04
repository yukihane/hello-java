package examples.jpa;

import examples.jpa.entity.Person;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hibernate.testing.transaction.TransactionUtil.doInHibernate;
import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

public class AppTest {

    @Getter(AccessLevel.PRIVATE)
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example.unit");

    @Getter
    private final SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

    @Test
    public void testApp() {
        doInJPA(this::getEntityManagerFactory, em -> {
            final Person p = new Person();
            em.persist(p);
        });

        doInHibernate(this::getSessionFactory, session -> {
            final List<Person> result = session.createQuery("select p from Person p", Person.class).list();
            result.forEach(p -> System.out.println(p.getId()));
        });
    }
}
