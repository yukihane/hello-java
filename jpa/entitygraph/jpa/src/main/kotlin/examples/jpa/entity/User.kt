package examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(name = "active", nullable = false)
    var active: Boolean,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
