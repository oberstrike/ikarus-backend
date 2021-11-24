package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.persistence.resources.ResourceRepository
import de.ma.ikarus.shared.PersistenceException
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


}