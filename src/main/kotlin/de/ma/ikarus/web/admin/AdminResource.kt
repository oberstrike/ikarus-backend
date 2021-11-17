package de.ma.ikarus.web.admin

import de.ma.ikarus.api.resources.admin.GetResourcesUseCase
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.web.shared.PagedRequest
import de.ma.ikarus.web.shared.toPagedParams
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import javax.ws.rs.BeanParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/api/admin/resources")
@Tags(Tag(name = "Admin", description = ""))
class AdminResource(
    private val getResourcesUseCase: GetResourcesUseCase
) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getResources(
        @BeanParam pagedRequest: PagedRequest
    ): PagedList<ResourceShow> = getResourcesUseCase.invoke(pagedRequest.toPagedParams())

}