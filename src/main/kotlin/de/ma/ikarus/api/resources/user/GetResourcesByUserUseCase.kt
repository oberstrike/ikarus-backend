package de.ma.ikarus.api.resources.user

import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.User

interface GetResourcesByUserUseCase{
    operator fun invoke(user: User, pagedParams: PagedParams): Result<PagedList<ResourceShow>>
}
