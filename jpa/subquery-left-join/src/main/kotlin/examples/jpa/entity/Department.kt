package examples.jpa.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
class Department(
    var name: String,

    @ManyToOne
    var company: Company,
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}
