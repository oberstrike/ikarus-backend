package de.ma.ikarus.api.resources.admin

import de.ma.ikarus.api.resources.data.ResourceShowDTO
import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.shared.PagedList

interface GetResourcesUseCase {
    fun invoke(pagedParams: PagedParams): PagedList<ResourceShow>
}