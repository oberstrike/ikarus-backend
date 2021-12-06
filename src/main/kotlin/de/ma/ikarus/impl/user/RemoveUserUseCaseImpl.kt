package de.ma.ikarus.impl.user

import de.ma.ikarus.api.user.RemoveUserUseCase
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.UserNotFoundException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RemoveUserUseCaseImpl(
    private val userGateway: UserGateway
) : RemoveUserUseCase {
    override fun invoke(userId: String): Result<Boolean> {
        val user = userGateway.getByUserId(userId) ?: return Result.failure(UserNotFoundException(userId))
        try {
            userGateway.remove(user)
        } catch (e: Exception) {
            Result.success(false)
        }
        return Result.success(true)
    }
}