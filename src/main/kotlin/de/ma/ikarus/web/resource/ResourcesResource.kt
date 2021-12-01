package de.ma.ikarus.web.resource

import de.ma.ikarus.api.resources.user.CreateResourceByUserUseCase
import de.ma.ikarus.api.resources.user.DeleteResourceUseCase
import de.ma.ikarus.api.resources.user.GetResourcesByUserUseCase
import de.ma.ikarus.api.resources.user.UpdateResourceUseCase
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.web.resource.dtos.ResourceCreateForm
import de.ma.ikarus.web.resource.dtos.ResourceDeleteForm
import de.ma.ikarus.web.resource.dtos.ResourceUpdateForm
import de.ma.ikarus.web.shared.PagedRequest
import de.ma.ikarus.web.shared.toPagedParams
import de.ma.ikarus.web.user.UserService
import de.ma.ikarus.web.user.UserServiceImpl
import io.quarkus.security.Authenticated
import io.quarkus.security.identity.SecurityIdentity
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/api/resources")
@Tags(Tag(name = "Resource", description = ""))
class ResourcesResource(
    private val getResourcesByUserUseCase: GetResourcesByUserUseCase,
    private val createResourceByUserUseCase: CreateResourceByUserUseCase,
    private val updateResourceUseCase: UpdateResourceUseCase,
    private val securityIdentity: SecurityIdentity,
    private val deleteResourceUseCase: DeleteResourceUseCase,
    userServiceImpl: UserServiceImpl
) : UserService by userServiceImpl {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @SecurityRequirements(
        value = [
            SecurityRequirement(name = "bearerAuth")
        ]
    )
    @Transactional
    fun getResources(
        @BeanParam @Valid
        pagedRequest: PagedRequest
    ): PagedList<ResourceShow> = withUser(securityIdentity) { user ->
        val result = getResourcesByUserUseCase(user, pagedRequest.toPagedParams())
        result.getOrNull() ?: throw NotFoundException("No resources found")
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Add a new resource",
        operationId = "addResource"
    )
    @SecurityRequirements(
        value = [
            SecurityRequirement(name = "bearerAuth")
        ]
    )
    @Transactional
    @Authenticated
    fun createResource(resourceDTO: ResourceCreateForm) = withUser(securityIdentity) { user ->
        val result = createResourceByUserUseCase(resourceDTO, user)
        result.getOrNull() ?: throw BadRequestException(result.exceptionOrNull()?.message ?: "No resource created")
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @SecurityRequirements(
        value = [
            SecurityRequirement(name = "bearerAuth")
        ]
    )
    @Transactional
    @Authenticated
    fun update(resourceUpdateForm: ResourceUpdateForm): ResourceShow = withUser(securityIdentity) { user ->
        val result = updateResourceUseCase(resourceUpdateForm, user)
        result.getOrNull() ?: throw result.exceptionOrNull() ?: throw BadRequestException("update failed")
    }


    @DELETE
    @SecurityRequirements(
        value = [
            SecurityRequirement(name = "bearerAuth")
        ]
    )
    @Transactional
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    fun delete(resourceDeleteForm: ResourceDeleteForm) {
        withUser(securityIdentity) { user ->
            try {
                deleteResourceUseCase(resourceDeleteForm, user)
            }catch (e: Exception) {
                throw BadRequestException(e.message ?: "delete failed")
            }
        }
    }


}