package de.ma.ikarus.persistence.resources

import de.ma.ikarus.persistence.user.UserEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*


@Table(name = "resource")
@Entity(name = "resource")
class ResourceEntity : PanacheEntity() {
    lateinit var name: String

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var user: UserEntity
}

