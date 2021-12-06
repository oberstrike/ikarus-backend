package de.ma.ikarus.impl.user

import de.ma.ikarus.api.user.CheckIfUserAlreadyExistsUseCase
import de.ma.ikarus.domain.user.UserGateway
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CheckIfUserAlreadyExistsUseCaseImpl(
    private val userGateway: UserGateway
) : CheckIfUserAlreadyExistsUseCase {

    override fun invoke(userId: String): Boolean {
        return userGateway.getByUserId(userId) != null
    }
}