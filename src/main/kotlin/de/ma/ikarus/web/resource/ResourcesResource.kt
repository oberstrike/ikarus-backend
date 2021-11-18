package de.ma.ikarus.web.resource

import de.ma.ikarus.api.resources.data.ResourceCreateDTO
import de.ma.ikarus.api.resources.data.ResourceUpdateDTO
import de.ma.ikarus.api.resources.user.CreateResourceByUserUseCase
import de.ma.ikarus.api.resources.user.GetResourcesByUserUseCase
import de.ma.ikarus.api.resources.user.UpdateResourceUseCase
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.web.shared.PagedRequest
import de.ma.ikarus.web.shared.toPagedParams
import io.quarkus.security.identity.SecurityIdentity
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api/resources")
@Tags(Tag(name = "Resource", description = ""))
class ResourcesResource(
    private val getResourcesByUserUseCase: GetResourcesByUserUseCase,
    private val createResourceByUserUseCase: CreateResourceByUserUseCase,
    private val updateResourceUseCase: UpdateResourceUseCase,
    private val securityIdentity: SecurityIdentity
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getResources(
        @BeanParam @Valid
        pagedRequest: PagedRequest
    ): PagedList<ResourceShow> = withUser(securityIdentity) { user ->
        val result = getResourcesByUserUseCase(user, pagedRequest.toPagedParams())
        result.getOrNull() ?: throw NotFoundException("No resources found")
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun createResource(resourceDTO: ResourceCreateDTO) = withUser(securityIdentity) { user ->
        val result = createResourceByUserUseCase(resourceDTO, user)
        result.getOrNull() ?: throw BadRequestException(result.exceptionOrNull()?.message ?: "No resource created")
    }

    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun update(resourceUpdateDTO: ResourceUpdateDTO): Response {
        val result = updateResourceUseCase(resourceUpdateDTO)
        return if (result.isSuccess) Response.status(Response.Status.OK).build()
        else Response.status(Response.Status.BAD_REQUEST)
            .entity(result.exceptionOrNull()?.message ?: "There was an error").build()

    }

    private fun <T> withUser(securityIdentity: SecurityIdentity, block: (UserDTO) -> T): T {
        return block(UserDTO("oberstrike"))
    }


}