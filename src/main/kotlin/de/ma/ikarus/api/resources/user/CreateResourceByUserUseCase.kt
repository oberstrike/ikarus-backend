package de.ma.ikarus.api.resources.user

import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow

interface CreateResourceByUserUseCase {
    operator fun invoke(resource: ResourceCreate, user: UserDTO): Result<ResourceShow>
}
