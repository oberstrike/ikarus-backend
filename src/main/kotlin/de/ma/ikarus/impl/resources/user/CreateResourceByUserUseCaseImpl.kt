package de.ma.ikarus.impl.resources.user

import de.ma.ikarus.api.resources.user.CreateResourceByUserUseCase
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.UserGateway
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateResourceByUserUseCaseImpl(
    private val resourceGateway: ResourceGateway,
    private val userGateway: UserGateway

) : CreateResourceByUserUseCase {

    override fun invoke(resource: ResourceCreate, user: UserDTO): ResourceShow {
        userGateway.getUserByName(user.name)

        return resourceGateway.createResource(
            resource
        )
    }
}