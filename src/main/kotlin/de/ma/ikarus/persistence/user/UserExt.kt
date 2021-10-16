package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.user.User


fun UserEntity.toUser(): User {
    return User(name!!)
}