package de.ma.ikarus.persistence.shared.data

import de.ma.ikarus.domain.resource.Resource

data class ResourceDTO(
    override val name: String,
    override val content: String,
    override var id: String?,
    override val version: Int
) : Resource