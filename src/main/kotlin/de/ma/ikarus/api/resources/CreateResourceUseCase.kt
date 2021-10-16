package de.ma.ikarus.api.resources

import de.ma.ikarus.api.user.UserDTO

interface CreateResourceUseCase {
    operator fun invoke(resource: ResourceCreateDTO, user: UserDTO)
}
