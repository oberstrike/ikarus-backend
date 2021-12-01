package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.resources.ResourceEntity
import de.ma.ikarus.persistence.shared.nanoid.AbstractNanoIdEntity
import javax.persistence.*

@Table(name = "ikarus_user")
@Entity(name = "ikarus_user")
class UserEntity(
    override var userId: String
):   AbstractNanoIdEntity(), User{

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var resources: MutableSet<ResourceEntity> = mutableSetOf()

    fun addResource(resource: ResourceEntity){
        resources.add(resource)
        resource.user = this
    }


}