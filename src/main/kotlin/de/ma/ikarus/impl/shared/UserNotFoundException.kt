package de.ma.ikarus.impl.shared

class UserNotFoundException(userId: String): RuntimeException(
    "The user with the id $userId was not found."
) {
}