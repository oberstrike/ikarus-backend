package de.ma.ikarus.persistence.user

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepository<UserEntity> {
    fun findByUserId(userId: String): UserEntity? {
        return find("userId", userId).firstResult()
    }

    fun save(user: UserEntity): UserEntity {
        persist(user)
        return user
    }
}