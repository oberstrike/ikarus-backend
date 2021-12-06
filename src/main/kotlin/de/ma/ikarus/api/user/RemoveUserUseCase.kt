package de.ma.ikarus.api.user

interface RemoveUserUseCase {
    operator fun invoke(userId: String): Result<Boolean>

}