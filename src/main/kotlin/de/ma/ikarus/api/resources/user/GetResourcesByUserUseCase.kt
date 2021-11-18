package de.ma.ikarus.api.resources.user

import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.ResourceShow

interface GetResourcesByUserUseCase{
    operator fun invoke(user: UserDTO, pagedParams: PagedParams): Result<PagedList<ResourceShow>>
}
