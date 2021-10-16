package de.ma.ikarus.persistence.user

import de.ma.ikarus.persistence.resources.ResourceEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.util.*
import javax.persistence.*

@Entity(name = "resource_user")
class UserEntity(
    var name: String? = null
): PanacheEntity(){

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var resources: MutableSet<ResourceEntity> = mutableSetOf()


}