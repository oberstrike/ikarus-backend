package de.ma.ikarus.api.resources.data

import de.ma.ikarus.domain.resource.ResourceCreate

data class ResourceCreateDTO(
    override val name: String = "",
    override val content: String = "",
) : ResourceCreate