package de.ma.ikarus.web.resource.dtos

import de.ma.ikarus.domain.resource.ResourceDelete

data class ResourceDeleteForm(
    override val id: String,
    override val version: Int
): ResourceDelete