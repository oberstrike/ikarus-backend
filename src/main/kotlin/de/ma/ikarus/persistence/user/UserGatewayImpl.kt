package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.resource.ResourceDelete
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.toNanoId
import de.ma.ikarus.persistence.resources.ResourceRepository
import de.ma.ikarus.persistence.shared.PersistenceException
import de.ma.ikarus.persistence.shared.nanoid.NanoIdEntity
import de.ma.ikarus.persistence.shared.toResourceShow
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UserGatewayImpl(
    private val userRepository: UserRepository,
    private val resourceRepository: ResourceRepository
) : UserGateway {

    @Transactional
    override fun addResourceToUser(user: User, resource: ResourceShow): ResourceShow {
        val persistedUser = userRepository.findByUserId(user.userId) ?: throw PersistenceException()
        val resourceEntity =
            resourceRepository.findById(resource.id.toNanoId()) ?: throw PersistenceException()
        persistedUser.addResource(resourceEntity)
        return resourceEntity.toResourceShow()
    }

    @Transactional
    override fun getByUserId(userId: String): User? {
        return userRepository.findByUserId(userId)
    }

    @Transactional
    override fun createUser(userId: String): User {
        return userRepository.save(UserEntity(userId))
    }

    override fun isAllowedToUpdate(user: User, resource: ResourceDelete): Boolean {
        //roles are missing right now so just check if the user owns the resource
        val userEntity = userRepository.findByUserId(user.userId) ?: throw PersistenceException("User not found")

        //if role is admin return true
        return userEntity.resources.any { it.id?.nanoId == resource.id }
    }

    override fun removeResourceFromUser(user: User, resource: ResourceDelete) {
        val userEntity = userRepository.findByUserId(user.userId) ?: throw PersistenceException("User not found")
        val resourceEntity =
            resourceRepository.findById(resource.id.toNanoId())
                ?: throw PersistenceException("Resource not found ${resource.id}")
        userEntity.resources.remove(resourceEntity)
        resourceEntity.user = null
    }

    override fun remove(user: User) {

        userRepository.deleteByUserId(user.userId)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll().list()
    }

}