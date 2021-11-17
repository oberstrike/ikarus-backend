package de.ma.ikarus.impl.resources.user

import de.ma.ikarus.api.resources.user.UpdateResourceUseCase
import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.resource.ResourceUpdate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UpdateResourceUseCaseImpl(
    private val resourceGateway: ResourceGateway
): UpdateResourceUseCase {

    override fun invoke(resource: ResourceUpdate): Result<Boolean> {
        val update = resourceGateway.update(resource)

        return if(update) Result.success(true) else Result.failure(RuntimeException("error"))
    }

}