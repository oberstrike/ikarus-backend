package de.ma.ikarus.api.resources.data

import de.ma.ikarus.domain.resource.ResourceUpdate
import javax.validation.constraints.NotNull

data class ResourceUpdateDTO(
    @NotNull
    override val version: Int,
    @NotNull
    override val id: String,
    @NotNull
    override val content: String,
    @NotNull
    override val name: String
): ResourceUpdate