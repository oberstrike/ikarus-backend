package de.ma.ikarus.persistence.user

import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.utils.resourceDelete
import de.ma.ikarus.persistence.resources.ResourceEntity
import de.ma.ikarus.persistence.resources.TransactionalQuarkusTest
import de.ma.ikarus.persistence.shared.data.ResourceUpdateDTO
import de.ma.ikarus.persistence.utils.AbstractDatabaseTest
import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

import javax.inject.Inject
import javax.persistence.EntityManager

@TransactionalQuarkusTest
class UserGatewayImplTest : AbstractDatabaseTest() {

    @Inject
    override lateinit var entityManager: EntityManager

    @Inject
    lateinit var userGateway: UserGateway

    @Test
    fun `checks if the user is allowed to edit`() = withUser(resourceEntity()) { user ->
        val resourceEntity = user.resources.first()

        val allowedToUpdate = userGateway.isAllowedToUpdate(
            user, ResourceUpdateDTO(
                resourceEntity.version,
                resourceEntity.id!!.nanoId,
                resourceEntity.content,
                resourceEntity.name
            )
        )

        allowedToUpdate shouldBe true
    }

    @Test
    fun `checks if the user is allowed to edit but is not`() = withResource { resourceEntity ->
        withUser { user ->
            val allowedToUpdate = userGateway.isAllowedToUpdate(
                user, ResourceUpdateDTO(
                    resourceEntity.version,
                    resourceEntity.id!!.nanoId,
                    resourceEntity.content,
                    resourceEntity.name
                )
            )

            allowedToUpdate shouldBe false
        }
    }

    @Test
    fun `registers a resource and delete it`() = withUser(resourceEntity()) {
        val resourceEntity = it.resources.first()
        val resourceDelete = resourceDelete(resourceEntity.id!!, resourceEntity.version)

        userGateway.removeResourceFromUser(it, resourceDelete)


        val userEntity = entityManager.find(UserEntity::class.java, it.id)

        userEntity.resources.size shouldBe 0

        entityManager.find(ResourceEntity::class.java, resourceEntity.id) `should not be` null

    }


}