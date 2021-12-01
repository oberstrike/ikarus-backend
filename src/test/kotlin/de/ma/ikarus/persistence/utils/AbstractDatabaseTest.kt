package de.ma.ikarus.persistence.utils

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.shared.NanoId
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.resources.ResourceEntity
import de.ma.ikarus.persistence.shared.nanoid.NanoIdEntity
import de.ma.ikarus.persistence.user.UserEntity
import io.github.serpro69.kfaker.Faker
import io.quarkus.test.common.QuarkusTestResource
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional


val faker = Faker()

@QuarkusTestResource(DatabaseTestResource::class)
abstract class AbstractDatabaseTest {

    abstract var entityManager: EntityManager

    private fun randomUUID() = UUID.randomUUID().toString()

    protected fun resourceEntities(count: Int): List<ResourceEntity> {
        return (1..count).map {
            resourceEntity()
        }
    }

    protected fun resourceEntity(): ResourceEntity {
        return faker.randomProvider.randomClassInstance() {

        }
    }

    fun withUser(block: (UserEntity) -> Unit) {
        return withUser(emptyList(), block)
    }

    fun withUser(resource: ResourceEntity, block: (UserEntity) -> Unit) {
        return withUser(listOf(resource), block)
    }

    @Transactional
    fun withUser(resources: List<ResourceEntity>, block: (UserEntity) -> Unit) {
        val user = UserEntity(randomUUID())

        resources.forEach {
            entityManager.persist(it)
        }

        resources.forEach(user::addResource)
        entityManager.persist(user)
        block(user)
    }

    @Transactional
    fun withResource(block: (Resource<NanoIdEntity>) -> Unit) {
        val resource = resourceEntity()
        entityManager.persist(resource)
        block(resource)
    }


    class UserWithResources(val user: User, val resources: List<Resource<NanoIdEntity>>)

}