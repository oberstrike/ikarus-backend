package de.ma.ikarus.domain.resource

import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.domain.shared.Sort
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.shared.PagedList


interface ResourceGateway {
    fun getResourcesByUser(
        sort: Sort = Sort.by(Sort.Direction.ASCENDING, "id"),
        user: User,
        params: PagedParams,
    ): PagedList<Resource>

    fun saveResource(resource: Resource): Resource

    fun getResources(
        sort: Sort = Sort.by(Sort.Direction.ASCENDING, "id"),
        params: PagedParams
    ): PagedList<Resource>

}