package examples.jpa

import examples.jpa.entity.Person
import jakarta.persistence.Persistence
import org.hibernate.SessionFactory
import org.hibernate.testing.transaction.TransactionUtil.doInHibernate
import org.hibernate.testing.transaction.TransactionUtil.doInJPA
import org.junit.jupiter.api.Test


class HelloTest {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("example.unit")

    private val sessionFactory = entityManagerFactory.unwrap(SessionFactory::class.java)

    @Test
    fun testApp() {
        doInJPA(this::entityManagerFactory) { em ->
            val p = Person()
            em.persist(p)
        }

        doInHibernate<Unit>(this::sessionFactory) { session ->
            val result: List<Person> = session.createQuery("select p from Person p", Person::class.java).list()
            result.forEach { println(it.id) }
        }
    }
}
