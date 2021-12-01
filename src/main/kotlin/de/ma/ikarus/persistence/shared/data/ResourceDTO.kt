package de.ma.ikarus.persistence.shared.data

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.shared.NanoId

data class ResourceDTO(
    override val name: String,
    override val content: String,
    override val version: Int,
    override var id: NanoId?
) : Resource<NanoId>