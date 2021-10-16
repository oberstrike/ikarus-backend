package de.ma.ikarus.web.resource

import de.ma.ikarus.api.resources.CreateResourceUseCase
import de.ma.ikarus.api.resources.GetResourcesByUserUseCase
import de.ma.ikarus.api.resources.ResourceCreateDTO
import de.ma.ikarus.api.resources.ResourceName
import de.ma.ikarus.api.shared.Response
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.web.shared.PagedRequest
import de.ma.ikarus.web.shared.toPagedParams
import io.quarkus.security.identity.SecurityIdentity
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags

@Path("/api/resources")
@Tags(Tag(name = "Resource", description = ""))
class ResourcesResource(
    private val getResourcesByUserUseCase: GetResourcesByUserUseCase,
    private val createResourceUseCase: CreateResourceUseCase,
    private val securityIdentity: SecurityIdentity
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getResources(
        @BeanParam pagedRequest: PagedRequest
    ): PagedList<ResourceName> = withUser(securityIdentity) { user ->
        when (val result = getResourcesByUserUseCase(user, pagedRequest.toPagedParams())) {
            is Response.Success -> result.value
            is Response.Failure -> throw result.throwable
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun createResource(resourceDTO: ResourceCreateDTO) = withUser(securityIdentity) { user ->
        createResourceUseCase(resourceDTO, user)
    }

    private fun <T> withUser(securityIdentity: SecurityIdentity, block: (UserDTO) -> T): T {
        return block(UserDTO("oberstrike"))
    }


}