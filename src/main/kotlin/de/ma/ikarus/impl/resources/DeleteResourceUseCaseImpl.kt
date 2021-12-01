package de.ma.ikarus.impl.resources

import de.ma.ikarus.api.resources.user.DeleteResourceUseCase
import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.NotAllowedToUpdateException
import de.ma.ikarus.impl.shared.OptimisticLockException
import de.ma.ikarus.impl.shared.ResourceNotFoundException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DeleteResourceUseCaseImpl(
    private val resourceGateway: ResourceGateway,
    private val userGateway: UserGateway
) : DeleteResourceUseCase {

    override fun invoke(resource: ResourceDelete, user: User) {
        //get the persisted resource
        val persistedResourceUseCase = resourceGateway.getResourceById(resource.id)

        //if the resource is not found, throw exception
        persistedResourceUseCase?: throw ResourceNotFoundException(resource.id.nanoId)

        //if the version are different, throw an OptimisticLockException{@link OptimisticLockException}
        if(persistedResourceUseCase.version != resource.version) throw OptimisticLockException()


        //if the user is not allowed to update the resource, throw exception
        val allowedToUpdate = userGateway.isAllowedToUpdate(user, resource)
        if (!allowedToUpdate) throw NotAllowedToUpdateException(user.userId, resource.id.nanoId)

        //remove the resource from the user
        userGateway.removeResourceFromUser(user, resource)
        //delete the resource
        resourceGateway.deleteResource(resource)
    }
}