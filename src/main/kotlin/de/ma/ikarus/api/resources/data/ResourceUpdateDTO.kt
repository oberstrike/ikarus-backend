package de.ma.ikarus.api.resources.data

import de.ma.ikarus.domain.resource.ResourceUpdate

data class ResourceUpdateDTO(
    override val version: Int,
    override val id: String,
    override val content: String,
    override val name: String
): ResourceUpdate