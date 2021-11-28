package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.shared.Sort
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.shared.PersistenceException
import de.ma.ikarus.persistence.shared.createToEntity
import de.ma.ikarus.persistence.shared.toPagedList
import de.ma.ikarus.persistence.shared.toResourceShow
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.shared.pagedMap
import javax.inject.Singleton

@Singleton
class ResourceGatewayImpl(
    private val resourceRepository: ResourceRepository
) : ResourceGateway {

    override fun getResourcesByUser(
        user: User,
        params: PagedParams,
        sort: Sort,
    ): PagedList<ResourceShow> {
        return resourceRepository.findAll().toPagedList(params).pagedMap(ResourceEntity::toResourceShow)
    }

    override fun createResource(resource: ResourceCreate): ResourceShow {
        val entity = resource.createToEntity()
        return resourceRepository.save(entity).toResourceShow()
    }

    override fun getResources(sort: Sort, params: PagedParams): PagedList<ResourceShow> {
        return resourceRepository.findAll().toPagedList(params).pagedMap(ResourceEntity::toResourceShow)
    }

    override fun update(resource: ResourceUpdate): ResourceShow {

        val saved = resourceRepository.findById(resource.id) ?: throw PersistenceException("Resource not found")

        if (resource.version != saved.version) {
            throw PersistenceException("Resource version mismatch")
        }

        //updates the resource
        saved.apply {
            name = resource.name
            content = resource.content
        }

        saved.persist()
        return saved.toResourceShow()
    }

}