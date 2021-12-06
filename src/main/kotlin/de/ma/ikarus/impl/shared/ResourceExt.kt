package de.ma.ikarus.impl.shared

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.shared.NanoId
import de.ma.ikarus.persistence.shared.data.NanoIdDTO
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO
import de.ma.ikarus.persistence.shared.data.ResourceShowDTO
import de.ma.ikarus.persistence.shared.nanoid.NanoIdEntity

fun ResourceCreate.toDTO(): ResourceCreateDTO {
    return ResourceCreateDTO(
        name,
        content
    )
}

fun Resource<NanoId>.toShowDTO(): ResourceShow {
    return ResourceShowDTO(
        content = content,
        name = name,
        version = version,
        id = id!!.nanoId
    )
}

fun String.toNanoId(): NanoId {
    return NanoIdEntity(this)
}