package de.ma.ikarus.impl.resources

import de.ma.ikarus.api.resources.CreateResourceUseCase
import de.ma.ikarus.api.resources.ResourceCreateDTO
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.user.UserGateway
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateResourceUseCaseImpl(
    private val resourceGateway: ResourceGateway,
    private val userGateway: UserGateway

) : CreateResourceUseCase {

    override fun invoke(resource: ResourceCreateDTO, user: UserDTO) {
        userGateway.getUserByName(user.name)

        resourceGateway.saveResource(
            Resource(resource.name)
        )
    }
}