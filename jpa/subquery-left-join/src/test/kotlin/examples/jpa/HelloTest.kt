package examples.jpa

import examples.jpa.entity.Company
import examples.jpa.entity.Department
import jakarta.persistence.Persistence
import org.hibernate.Session
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

            val c1 = Company("Has Departments")
            em.persist(c1)

            val c2 = Company("None")
            em.persist(c2)

            (1..2).forEach {
                val d = Department("Department $it", c1)
                em.persist(d)
            }
        }

        doInHibernate<Unit>(this::sessionFactory) { session: Session ->
            val queryString = """
                select distinct c from Company c
                left join fetch c.departments d
                where d.name = :departmentName or d is null
            """.trimIndent()
            session.createQuery(queryString, Company::class.java)
                .setParameter("departmentName", "Department 1")
                .list().forEach {
                    println(it.name)
                    it.departments.forEach { d ->
                        println("\t${d.name}")
                    }
                }
        }
    }
}
