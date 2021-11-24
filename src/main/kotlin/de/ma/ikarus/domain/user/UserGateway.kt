package de.ma.ikarus.domain.user

import de.ma.ikarus.domain.resource.Resource

interface UserGateway {

    fun addResourceToUser(user: User, resource: Resource): Resource

    fun getByUserId(userId: String): User?

    fun createUser(userId: String): User
}