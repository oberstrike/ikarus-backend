package de.ma.ikarus.impl.resources.user

import de.ma.ikarus.api.resources.user.CreateResourceByUserUseCase
import de.ma.ikarus.api.user.UserDTO
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.ValidatedUseCase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateResourceByUserUseCaseImpl(
    private val resourceGateway: ResourceGateway,
    private val userGateway: UserGateway,
    private val validatedUseCase: ValidatedUseCase
) : CreateResourceByUserUseCase {

    override fun invoke(
        resource: ResourceCreate,
        user: UserDTO
    ): Result<ResourceShow> = validatedUseCase.withValidated(resource) {
        userGateway.getUserByName(user.name)
        return@withValidated resourceGateway.createResource(
            resource
        )
    }
}