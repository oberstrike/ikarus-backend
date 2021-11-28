package de.ma.ikarus.impl.resources

import de.ma.ikarus.api.resources.user.CreateResourceByUserUseCase
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.ValidatedUseCase
import de.ma.ikarus.impl.shared.toDTO
import de.ma.ikarus.impl.shared.toShowDTO
import de.ma.ikarus.persistence.shared.showToEntity
import de.ma.ikarus.persistence.shared.createToEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateResourceByUserUseCaseImpl(
    private val resourceGateway: ResourceGateway,
    private val userGateway: UserGateway,
    private val validatedUseCase: ValidatedUseCase
) : CreateResourceByUserUseCase {

    override fun invoke(
        resource: ResourceCreate,
        user: User
    ): Result<ResourceShow> {

        return validatedUseCase.withValidated(resource.toDTO()) {
            val persistedResource = resourceGateway.createResource(resource.createToEntity())

            return@withValidated userGateway.addResourceToUser(
                user,
                persistedResource.showToEntity()
            ).toShowDTO()
        }
    }
}