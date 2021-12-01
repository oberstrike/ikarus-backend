package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.shared.NanoId
import de.ma.ikarus.persistence.shared.nanoid.AbstractNanoIdEntity
import de.ma.ikarus.persistence.shared.nanoid.NanoIdEntity
import de.ma.ikarus.persistence.user.UserEntity
import org.hibernate.Hibernate
import javax.persistence.*


@Table(name = "resource")
@Entity(name = "resource")
data class ResourceEntity(
    override var content: String = "",
    override var name: String = "",
) : AbstractNanoIdEntity(), Resource<NanoIdEntity> {

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
     var user: UserEntity? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ResourceEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()


    override fun toString(): String {
        return "ResourceEntity(id=$id, name='$name', content='$content')"
    }

}

