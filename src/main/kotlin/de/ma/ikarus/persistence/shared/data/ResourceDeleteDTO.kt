package de.ma.ikarus.persistence.shared.data

import de.ma.ikarus.domain.resource.ResourceDelete

data class ResourceDeleteDTO(
    override val id: String,
    override val version: Int
): ResourceDelete