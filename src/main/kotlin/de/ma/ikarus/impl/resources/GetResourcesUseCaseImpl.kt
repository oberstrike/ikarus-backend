package de.ma.ikarus.impl.resources

import de.ma.ikarus.api.resources.GetResourcesUseCase
import de.ma.ikarus.api.resources.ResourceName
import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.impl.shared.toResourceName
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.shared.pagedMap
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetResourcesUseCaseImpl(
    private val resourceGateway: ResourceGateway
) : GetResourcesUseCase {
    override fun invoke(pagedParams: PagedParams): PagedList<ResourceName> {
        return resourceGateway.getResources(
            params = pagedParams
        ).pagedMap(Resource::toResourceName)
    }
}