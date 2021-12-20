package de.ma.ikarus.persistence.resources

import de.ma.ikarus.persistence.shared.nanoid.AbstractBaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "ikarus_resource_content")
@Entity(name = "content")
class ContentEntity : AbstractBaseEntity() {

    var content: String? = null

}
