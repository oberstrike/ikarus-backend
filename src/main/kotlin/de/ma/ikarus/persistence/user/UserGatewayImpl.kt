package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.user.User
import de.ma.ikarus.domain.user.UserGateway
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserGatewayImpl(
    private val userRepository: UserRepository
) : UserGateway {

    override fun getUserByName(name: String): User {
        return (userRepository.findByName(name) ?: userRepository.save(UserEntity(name = name))).toUser()
    }


}