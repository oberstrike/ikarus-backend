package de.ma.ikarus.web.user

import de.ma.ikarus.domain.user.User
import io.quarkus.security.identity.SecurityIdentity

interface UserService {

    fun <T> withUser(securityIdentity: SecurityIdentity, block: (User) -> T): T

}