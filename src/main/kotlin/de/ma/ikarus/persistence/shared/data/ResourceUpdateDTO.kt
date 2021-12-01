package de.ma.ikarus.persistence.shared.data

import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.shared.NanoId
import javax.validation.constraints.NotNull

data class ResourceUpdateDTO(
    @NotNull
    override val version: Int,
    @NotNull
    override val id: NanoId,
    @NotNull
    override val content: String,
    @NotNull
    override val name: String
): ResourceUpdate