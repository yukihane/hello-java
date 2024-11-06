package examples.jpa.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Company(
    var name: String,

    @OneToMany(mappedBy = "company")
    var departments: MutableSet<Department> = mutableSetOf(),
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}
