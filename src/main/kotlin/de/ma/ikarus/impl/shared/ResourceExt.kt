package de.ma.ikarus.impl.shared

import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO

fun ResourceCreate.toDTO(): ResourceCreateDTO {
    return ResourceCreateDTO(
        name,
        content
    )
}