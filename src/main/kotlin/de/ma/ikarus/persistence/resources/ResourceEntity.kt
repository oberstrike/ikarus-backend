package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.persistence.shared.Monitored
import de.ma.ikarus.persistence.shared.MonitoredEntity
import de.ma.ikarus.persistence.shared.nanoid.*
import de.ma.ikarus.persistence.user.UserEntity
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import javax.persistence.*


@Table(name = "ikarus_resource")
@Entity(name = "resource")
@AttributeOverrides(
    value = [
        AttributeOverride(name ="nanoId", column = Column(name = "resource_id")),
    ]
)
data class ResourceEntity(
    override var content: String = "",
    override var name: String = ""
) : AbstractBaseEntity(),
    Resource<NanoIdEntity>,
    Monitored by MonitoredEntity(),
    Versionable by VersionEntity() {

    @get:JoinColumn
    @get:ManyToOne(fetch = FetchType.LAZY)
    var user: UserEntity? = null

    //equals and hashcode based on NanoId
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}

