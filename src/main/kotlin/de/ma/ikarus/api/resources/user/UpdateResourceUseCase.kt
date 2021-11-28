package de.ma.ikarus.api.resources.user

import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.user.User

interface UpdateResourceUseCase {
    operator fun invoke(resource: ResourceUpdate, user: User): Result<ResourceShow>
}