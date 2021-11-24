package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.persistence.shared.AbstractNanoIdEntity
import de.ma.ikarus.persistence.user.UserEntity
import org.hibernate.Hibernate
import javax.persistence.*


@Table(name = "resource")
@Entity(name = "resource")
data class ResourceEntity(
    override var content: String = "",
    override var name: String = "",
) : AbstractNanoIdEntity(), Resource {

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var user: UserEntity

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ResourceEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , version = $version )"
    }

}

