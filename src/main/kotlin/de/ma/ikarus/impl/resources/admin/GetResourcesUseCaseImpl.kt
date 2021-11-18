package de.ma.ikarus.impl.resources.admin

import de.ma.ikarus.api.resources.admin.GetResourcesUseCase
import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.shared.PagedList
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetResourcesUseCaseImpl(
    private val resourceGateway: ResourceGateway
) : GetResourcesUseCase {
    override fun invoke(pagedParams: PagedParams): PagedList<ResourceShow> {
        return resourceGateway.getResources(
            params = pagedParams
        )
    }
}