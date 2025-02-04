package examples.jpa;

import examples.jpa.entity.Child;
import examples.jpa.entity.Parent;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

public class AppTest {

    @Getter(AccessLevel.PRIVATE)
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example.unit");

    @Getter
    private final SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

    @Test
    public void testApp() {
        doInJPA(this::getEntityManagerFactory, em -> {
            final Parent p = new Parent();
            p.setName("parent");

            final var c = new Child();
            c.setName("child");
            p.setChild(c);

            em.persist(p);
        });

        doInJPA(this::getEntityManagerFactory, em -> {
            var parent = em.find(Parent.class, 1L);
            // ↓にブレークポイントを仕掛け動作を止めたうえで、parent変数を展開しchild変数を参照すると
            // childテーブルへのselectが走る
            System.out.println(parent.getId());
        });
    }
}
