package examples.jpa

import examples.jpa.entity.Department
import examples.jpa.entity.User
import org.hibernate.SessionFactory
import org.hibernate.testing.transaction.TransactionUtil.doInJPA
import org.junit.jupiter.api.Test
import javax.persistence.Persistence


class HelloTest {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("example.unit")

    private val sessionFactory = entityManagerFactory.unwrap(SessionFactory::class.java)

    @Test
    fun testApp() {
        doInJPA(this::entityManagerFactory) { em ->
            val d = Department()
            val u = listOf(
                User(department = d, active = true),
                User(department = d, active = true),
                User(department = d, active = false),
            )
            em.persist(d)
            u.forEach { em.persist(it) }
        }

        doInJPA<Unit>(this::entityManagerFactory) { em ->
            val graph = em.getEntityGraph("department.users")
            val query = em.createQuery(
                "select distinct d from Department d left join d.users u on u.active = true",
                Department::class.java
            )
            query.setHint("javax.persistence.fetchgraph", graph);
            val results = query.getResultList()

            println("size: ${results.size}")
            results.forEach { d ->
                println(d.users)
            }
        }
    }
}
