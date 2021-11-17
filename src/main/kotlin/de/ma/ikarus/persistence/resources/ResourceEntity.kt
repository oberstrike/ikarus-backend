package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.persistence.shared.AbstractNanoIdEntity
import de.ma.ikarus.persistence.user.UserEntity
import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import javax.persistence.*


@Table(name = "resource")
@Entity(name = "resource")
class ResourceEntity : Resource,  AbstractNanoIdEntity() {

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var user: UserEntity

    override lateinit var content: String

    override lateinit var name: String


}

