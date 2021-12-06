package de.ma.ikarus.domain.user

import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.resource.ResourceShow

interface UserGateway {

    fun getAllUsers(): List<User>

    fun addResourceToUser(user: User, resource: ResourceShow): ResourceShow

    fun getByUserId(userId: String): User?

    fun createUser(userId: String): User

    fun isAllowedToUpdate(user: User, resource: ResourceDelete): Boolean

    fun removeResourceFromUser(user: User, resource: ResourceDelete)

    fun remove(user: User)

}