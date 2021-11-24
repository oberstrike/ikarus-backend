package de.ma.ikarus.impl.user

import de.ma.ikarus.api.user.CheckIfUserExistsUseCase
import de.ma.ikarus.domain.user.UserGateway
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CheckIfUserExistsUseCaseImpl(
    private val userGateway: UserGateway
) : CheckIfUserExistsUseCase {

    override fun invoke(userId: String): Boolean {
        return userGateway.getByUserId(userId) != null
    }
}