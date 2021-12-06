package de.ma.ikarus.api.admin

import de.ma.ikarus.domain.user.User

interface GetAllUserUseCase {
    operator fun invoke(): List<User>
}