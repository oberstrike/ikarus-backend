package de.ma.ikarus.web.utils

import de.ma.ikarus.domain.user.User
import de.ma.ikarus.web.user.UserForm

data class RegisteredKeycloakUser(
    val username: String,
    val password: String,
    val role: String
)

object KeycloakAccountManager {

    val registeredUsers = mutableListOf(
        RegisteredKeycloakUser("user", "user", "user"),
        RegisteredKeycloakUser("user2", "mewtu123", "user")
    )

    operator fun get(id: Int): RegisteredKeycloakUser {
        return registeredUsers.getOrNull(id) ?: throw IllegalArgumentException("User with id $id not found")
    }

}