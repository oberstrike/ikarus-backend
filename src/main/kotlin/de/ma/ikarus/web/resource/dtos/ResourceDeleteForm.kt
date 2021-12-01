package de.ma.ikarus.web.resource.dtos

import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.shared.NanoId

data class ResourceDeleteForm(
    override val id: NanoId,
    override val version: Int
): ResourceDelete