package de.ma.ikarus.persistence.user

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepository<UserEntity> {
    fun findByName(name: String): UserEntity? {
        return find("name", name).firstResult()
    }

    fun save(user: UserEntity): UserEntity {
        persist(user)
        return user
    }
}