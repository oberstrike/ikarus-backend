package de.ma.ikarus.api.user

fun interface CheckIfUserExistsUseCase {
    operator fun invoke(userId: String): Boolean
}