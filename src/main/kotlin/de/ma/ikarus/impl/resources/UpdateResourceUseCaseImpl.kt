package de.ma.ikarus.impl.resources

import de.ma.ikarus.api.resources.user.UpdateResourceUseCase
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.NotAllowedToUpdateException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UpdateResourceUseCaseImpl(
    private val resourceGateway: ResourceGateway,
    private val userGateway: UserGateway
) : UpdateResourceUseCase {

    override fun invoke(resource: ResourceUpdate, user: User): Result<ResourceShow> {
        return try {
            //only the user wich owns the resource is allowed to update
            val isAllowedToUpdate = userGateway.isAllowedToUpdate(user, resource)
            if (isAllowedToUpdate) {
                Result.success(resourceGateway.update(resource))
            } else {
                throw NotAllowedToUpdateException(
                    userId = user.userId,
                    resourceId = resource.id.nanoId
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}