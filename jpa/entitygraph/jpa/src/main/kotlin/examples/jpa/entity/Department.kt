package examples.jpa.entity

import javax.persistence.*

@NamedEntityGraph(
    name = "department.users",
    attributeNodes = [NamedAttributeNode("users")]
)
@Entity
@Table(name = "departments")
class Department(
    @OneToMany(mappedBy = "department")
    var users: MutableSet<User> = mutableSetOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
