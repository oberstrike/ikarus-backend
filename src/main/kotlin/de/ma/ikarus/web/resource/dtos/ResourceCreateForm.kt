package de.ma.ikarus.web.resource.dtos

import de.ma.ikarus.domain.resource.ResourceCreate

data class ResourceCreateForm(
    override val name: String = "",
    override val content: String = "",
) : ResourceCreate