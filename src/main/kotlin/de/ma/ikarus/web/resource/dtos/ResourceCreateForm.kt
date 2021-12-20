package de.ma.ikarus.web.resource.dtos

import de.ma.ikarus.domain.resource.ResourceCreate
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Schema

data class ResourceCreateForm(
    override val name: String = "",
    override val content: String = "",
    @Schema(type = SchemaType.STRING, format = "binary", description = "file data")
    val attachment: String = ""
) : ResourceCreate