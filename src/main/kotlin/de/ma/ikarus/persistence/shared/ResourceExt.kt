package de.ma.ikarus.persistence.shared

import de.ma.ikarus.api.resources.data.ResourceShowDTO
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.persistence.resources.ResourceEntity

fun ResourceCreate.toEntity(): ResourceEntity {
    val target = ResourceEntity()
    target.name = name
    target.content = content
    return target
}

fun ResourceEntity.toResourceShow() = ResourceShowDTO(
    content = content,
    name = name,
    version = version,
    id = id!!
)
