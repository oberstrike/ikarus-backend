package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.shared.NanoId
import de.ma.ikarus.persistence.shared.PersistenceException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UserRepository : PanacheRepositoryBase<UserEntity, NanoId> {

    @Transactional
    fun findByUserId(userId: String): UserEntity? {
        return find("userId", userId).firstResult()
    }

    @Transactional
    fun save(user: UserEntity): UserEntity {
        persist(user)
        return user
    }

    @Transactional
    fun deleteByUserId(userId: String): Boolean {
        return try {
            val user = findByUserId(userId) ?: throw PersistenceException("User $userId not found")
            user.resources.forEach {
                it.user = null
            }
            user.resources.clear()
            delete(user)
            true
        } catch (e: Exception) {
            false
        }

    }
}