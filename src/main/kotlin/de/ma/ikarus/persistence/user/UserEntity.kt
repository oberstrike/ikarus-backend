package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.resources.ResourceEntity
import de.ma.ikarus.persistence.shared.nanoid.AbstractBaseEntity
import de.ma.ikarus.persistence.shared.nanoid.NanoIdentifiable
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import javax.persistence.*

@Table(name = "ikarus_user")
@Entity(name = "ikarus_user")
data class UserEntity(
    override var userId: String
) : AbstractBaseEntity(),
    User {

    @get:OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    var resources: MutableSet<ResourceEntity> = mutableSetOf()

    fun addResource(resource: ResourceEntity) {
        resources.add(resource)
        resource.user = this
    }

    //equals and hashcode based on NanoId
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}