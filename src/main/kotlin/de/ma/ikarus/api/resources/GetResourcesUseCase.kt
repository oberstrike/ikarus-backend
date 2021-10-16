package de.ma.ikarus.api.resources

import de.ma.ikarus.api.shared.PagedParams
import de.ma.ikarus.shared.PagedList

interface GetResourcesUseCase {
    fun invoke(pagedParams: PagedParams): PagedList<ResourceName>
}