package de.ma.ikarus.domain.user

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.shared.NanoId

interface UserGateway {

    fun addResourceToUser(user: User, resource: ResourceShow): ResourceShow

    fun getByUserId(userId: String): User?

    fun createUser(userId: String): User

    fun isAllowedToUpdate(user: User, resource: ResourceDelete): Boolean

    fun removeResourceFromUser(user: User, resource: ResourceDelete)
}