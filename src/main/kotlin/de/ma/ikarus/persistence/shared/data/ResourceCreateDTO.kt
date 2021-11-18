package de.ma.ikarus.persistence.shared.data

import de.ma.ikarus.domain.resource.ResourceCreate
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ResourceCreateDTO(
    @field:NotEmpty @field:NotNull @field:Length(min = 1, max = 255)
    override val name: String = "",
    @field:NotEmpty @field:NotNull
    override val content: String = "",
) : ResourceCreate