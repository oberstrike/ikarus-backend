package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.persistence.resources.ResourceRepository
import de.ma.ikarus.persistence.shared.PersistenceException
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UserGatewayImpl(
    private val userRepository: UserRepository,
    private val resourceRepository: ResourceRepository
) : UserGateway {

    @Transactional
    override fun addResourceToUser(user: User, resource: Resource): Resource {
        val persistedUser = userRepository.findByUserId(user.userId) ?: throw PersistenceException()
        val resourceEntity =
            resourceRepository.findById(resource.id ?: throw PersistenceException()) ?: throw PersistenceException()
        persistedUser.addResource(resourceEntity)
        return resourceEntity
    }

    @Transactional
    override fun getByUserId(userId: String): User? {
        return userRepository.findByUserId(userId)
    }

    @Transactional
    override fun createUser(userId: String): User {
        return userRepository.save(UserEntity(userId))
    }

    override fun isAllowedToUpdate(user: User, resource: ResourceUpdate): Boolean {
        //roles are missing right now so just check if the user owns the resource
        val userEntity = userRepository.findByUserId(user.userId) ?: throw PersistenceException("User not found")

        //if role is admin return true
        return userEntity.resources.any { it.id == resource.id }
    }


}