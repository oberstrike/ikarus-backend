package de.ma.ikarus.api.user

fun interface CheckIfUserAlreadyExistsUseCase {
    operator fun invoke(userId: String): Boolean
}