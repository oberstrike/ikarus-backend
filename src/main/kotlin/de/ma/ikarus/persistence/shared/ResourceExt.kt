package de.ma.ikarus.persistence.shared

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.persistence.resources.ResourceEntity

fun Resource.toEntity(): ResourceEntity {
    val target = ResourceEntity()
    target.name = name
    return target
}

fun ResourceEntity.toResource() = Resource(
    name,
    id?: 0
)
