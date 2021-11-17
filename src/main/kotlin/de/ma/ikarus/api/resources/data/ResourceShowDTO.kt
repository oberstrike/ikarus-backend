package de.ma.ikarus.api.resources.data

import de.ma.ikarus.domain.resource.ResourceShow

data class ResourceShowDTO(
    override val content: String,
    override val name: String,
    override val version: Int,
    override val id: String
) : ResourceShow