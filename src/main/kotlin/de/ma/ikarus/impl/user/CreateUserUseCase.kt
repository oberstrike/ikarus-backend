package de.ma.ikarus.impl.user

import de.ma.ikarus.api.user.CreateUserUseCase
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.AlreadyExistsException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CreateUserUseCaseImpl(
    private val userGateway: UserGateway
) : CreateUserUseCase {
    override fun invoke(userId: String): Result<User> {
        val oldUser = userGateway.getByUserId(userId)
        if(oldUser != null){
            return Result.failure(AlreadyExistsException("User"))
        }
        return Result.success(userGateway.createUser(userId))

    }
}