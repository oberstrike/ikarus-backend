package de.ma.ikarus.web.resource.dtos

import de.ma.ikarus.domain.resource.ResourceUpdate

data class ResourceUpdateForm(
    override val version: Int,
    override val id: String,
    override val content: String,
    override val name: String
): ResourceUpdate