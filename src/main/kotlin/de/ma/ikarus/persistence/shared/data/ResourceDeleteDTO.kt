package de.ma.ikarus.persistence.shared.data

import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.shared.NanoId

data class ResourceDeleteDTO(
    override val id: NanoId,
    override val version: Int
): ResourceDelete