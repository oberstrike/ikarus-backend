package de.ma.ikarus.persistence.shared

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.persistence.shared.data.ResourceShowDTO
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.persistence.resources.ResourceEntity
import de.ma.ikarus.persistence.shared.data.ResourceDeleteDTO
import de.ma.ikarus.persistence.shared.nanoid.NanoIdEntity


fun Resource<*>.mainToEntity(): ResourceEntity {
    val resourceEntity = ResourceEntity(
        content = content,
        name = name
    )
    resourceEntity.id = id?.nanoId?.let { NanoIdEntity(it) }
    return resourceEntity
}

fun ResourceCreate.createToEntity(): ResourceEntity {
    val target = ResourceEntity()
    target.name = name
    target.content = content
    return target
}

fun ResourceShow.showToEntity(): ResourceEntity {
    val target = ResourceEntity()
    target.id = NanoIdEntity(id.nanoId)
    target.name = name
    target.content = content
    return target
}

fun Resource<*>.toResourceShow() = ResourceShowDTO(
    content = content,
    name = name,
    version = version,
    id = id!!
)

fun Resource<*>.toResourceDelete() = ResourceDeleteDTO(
    id = id!!,
    version = version
)