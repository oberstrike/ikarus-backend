package de.ma.ikarus.impl.shared

import de.ma.ikarus.api.resources.ResourceName
import de.ma.ikarus.domain.resource.Resource

fun Resource.toResourceName(): ResourceName {
    return ResourceName(this.name, this.id)
}