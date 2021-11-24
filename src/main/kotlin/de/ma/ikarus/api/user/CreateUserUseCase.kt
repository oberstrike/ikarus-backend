package de.ma.ikarus.api.user

import de.ma.ikarus.domain.user.User

interface CreateUserUseCase {
    operator fun invoke(userId: String): Result<User>
}