package de.ma.ikarus.impl.resources

import de.ma.ikarus.api.resources.GetResourcesByUserUseCase
import de.ma.ikarus.api.resources.ResourceName
import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.api.shared.Response
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.impl.shared.toResourceName
import de.ma.ikarus.shared.pagedMap
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.NotFoundException

@ApplicationScoped
class GetResourcesByUserUseCaseImpl(
    private val resourceGateway: ResourceGateway
) : GetResourcesByUserUseCase {

    override fun invoke(user: UserDTO, pagedParams: PagedParams): Response<PagedList<ResourceName>> {
        val resources = resourceGateway.getResourcesByUser(user = User(user.name), params = pagedParams).pagedMap(Resource::toResourceName)
        return if (resources.content.isEmpty()) {
            Response.Failure(NotFoundException("No resources found"))
        } else {
            Response.Success(resources)
        }
    }

}