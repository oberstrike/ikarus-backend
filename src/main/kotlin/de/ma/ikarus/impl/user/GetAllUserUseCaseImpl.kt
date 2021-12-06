package de.ma.ikarus.impl.user

import de.ma.ikarus.api.admin.GetAllUserUseCase
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetAllUserUseCaseImpl(
    private val userGateway: UserGateway
) : GetAllUserUseCase {

    override fun invoke(): List<User> {
        return userGateway.getAllUsers()
    }
}