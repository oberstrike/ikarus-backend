package de.ma.ikarus.persistence.utils

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.resources.DatabaseTestResource
import de.ma.ikarus.persistence.resources.ResourceEntity
import de.ma.ikarus.persistence.resources.TransactionalQuarkusTest
import de.ma.ikarus.persistence.user.UserEntity
import io.quarkus.test.common.QuarkusTestResource
import java.util.*
import java.util.UUID.randomUUID
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceUnit
import javax.transaction.Transactional


@QuarkusTestResource(DatabaseTestResource::class)
abstract class AbstractRepositoryTest {

    abstract var entityManager: EntityManager

    private fun randomUUID() = UUID.randomUUID().toString()

    protected fun resourceEntity(): ResourceEntity {
        return ResourceEntity("content", "name")
    }

    fun withUser(resource: ResourceEntity, block: (User) -> Unit) {
        return withUser(listOf(resource), block)
    }

    @Transactional
    fun withUser(resources: List<ResourceEntity>, block: (User) -> Unit) {
        val user = UserEntity(randomUUID())

        resources.forEach {
            entityManager.persist(it)
        }

        resources.forEach(user::addResource)
        entityManager.persist(user)
        block(user)
    }

    @Transactional
    fun withResource(block: (Resource) -> Unit) {
        val resource = resourceEntity()
        entityManager.persist(resource)
        block(resource)
    }


}