package de.ma.ikarus.web.resource.dtos

import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.shared.NanoId

data class ResourceUpdateForm(
    override val version: Int,
    override val id: NanoId,
    override val content: String,
    override val name: String
): ResourceUpdate