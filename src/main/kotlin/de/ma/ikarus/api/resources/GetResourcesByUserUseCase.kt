package de.ma.ikarus.api.resources

import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.api.shared.Response
import de.ma.ikarus.api.user.UserDTO

interface GetResourcesByUserUseCase{
    operator fun invoke(user: UserDTO, pagedParams: PagedParams): Response<PagedList<ResourceName>>
}
