package de.ma.ikarus.impl.resources.user

import de.ma.ikarus.api.resources.user.GetResourcesByUserUseCase
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.shared.PagedList
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.NotFoundException

@ApplicationScoped
class GetResourcesByUserUseCaseImpl(
    private val resourceGateway: ResourceGateway
) : GetResourcesByUserUseCase {

    override fun invoke(user: User, pagedParams: PagedParams): Result<PagedList<ResourceShow>> {

        val resources = resourceGateway.getResourcesByUser(
            user = user,
            params = pagedParams
        )

        return if (resources.content.isEmpty()) {
            Result.failure(NotFoundException("No resources found"))
        } else {
            Result.success(resources)
        }
    }

}