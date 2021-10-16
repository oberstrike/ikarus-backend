package de.ma.ikarus.persistence.resources

import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.shared.Sort
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.shared.toEntity
import de.ma.ikarus.persistence.shared.toPagedList
import de.ma.ikarus.persistence.shared.toResource
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.shared.pagedMap
import javax.inject.Singleton

@Singleton
class ResourceGatewayImpl(
    private val resourceRepository: ResourceRepository
) : ResourceGateway {

    override fun getResourcesByUser(
        sort: Sort,
        user: User,
        params: PagedParams,
    ): PagedList<Resource> {
        return resourceRepository.findAll().toPagedList(params).pagedMap(ResourceEntity::toResource)
    }

    override fun saveResource(resource: Resource): Resource {
        resourceRepository.persist(resource.toEntity())
        return resource
    }

    override fun getResources(sort: Sort, params: PagedParams): PagedList<Resource> {
        return resourceRepository.findAll().toPagedList(params).pagedMap(ResourceEntity::toResource)
    }


}