package de.ma.ikarus.domain.resource

import de.ma.ikarus.domain.shared.NanoId
import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.domain.shared.Sort
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.shared.PagedList


interface ResourceGateway {

    fun getResourcesByUser(
        user: User,
        params: PagedParams,
        sort: Sort = Sort.by(Sort.Direction.ASCENDING, "id"),
        ): PagedList<ResourceShow>

    fun createResource(resource: ResourceCreate): ResourceShow

    fun getResources(
        sort: Sort = Sort.by(Sort.Direction.ASCENDING, "id"),
        params: PagedParams
    ): PagedList<ResourceShow>

    fun update(resource: ResourceUpdate): ResourceShow

    fun getResourceById(id: NanoId): ResourceShow?

    fun deleteResource(resource: ResourceDelete)

}