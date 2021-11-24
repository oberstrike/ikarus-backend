package de.ma.ikarus.impl.shared

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO
import de.ma.ikarus.persistence.shared.data.ResourceShowDTO

fun ResourceCreate.toDTO(): ResourceCreateDTO {
    return ResourceCreateDTO(
        name,
        content
    )
}

fun Resource.toShowDTO(): ResourceShow {
    return ResourceShowDTO(
        content = content,
        name = name,
        version = version,
        id = id!!
    )
}