package de.ma.ikarus.api.resources.user

import de.ma.ikarus.domain.resource.ResourceUpdate

interface UpdateResourceUseCase {
    operator fun invoke(resource: ResourceUpdate): Result<Boolean>
}