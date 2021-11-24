package de.ma.ikarus.api.resources.user

import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.User

interface CreateResourceByUserUseCase {
    operator fun invoke(resource: ResourceCreate, user: User): Result<ResourceShow>
}
