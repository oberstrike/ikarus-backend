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
import org.apache.commons.io.IOUtils
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm
import org.jboss.resteasy.annotations.providers.multipart.PartType
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput
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
            } catch (e: Exception) {
                throw BadRequestException(e.message ?: "delete failed")
            }
        }
    }


    @POST
    @Path("/upload2")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    fun addFile2(
        file: String
        ): String {

        return file
    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RequestBody(
        content = [Content(
            mediaType = MediaType.MULTIPART_FORM_DATA,
            schema = Schema(implementation = MultipartBody::class)
        )]
    )
    fun addFile(@MultipartForm multipartBody: MultipartFormDataInput) {

        println(multipartBody)
    }

    class MultipartBody {
        @FormParam("file")
        @Schema(type = SchemaType.STRING, format = "binary", description = "file data")
        var file: String? = null

        @FormParam("fileName")
        @PartType(MediaType.TEXT_PLAIN)
        var fileName: String? = null
    }

}