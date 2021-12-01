package de.ma.ikarus.api.resources.user

import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.user.User

interface DeleteResourceUseCase {
    operator fun invoke(resource: ResourceDelete, user: User)
}