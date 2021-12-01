package de.ma.ikarus.persistence.resources

import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO
import de.ma.ikarus.persistence.shared.data.ResourceDeleteDTO
import de.ma.ikarus.persistence.shared.data.ResourceUpdateDTO
import de.ma.ikarus.persistence.shared.toResourceDelete
import de.ma.ikarus.persistence.shared.toResourceShow
import de.ma.ikarus.persistence.utils.AbstractDatabaseTest
import de.ma.ikarus.shared.PagedParams
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceUnit


@TransactionalQuarkusTest
class ResourceGatewayImplTest : AbstractDatabaseTest() {

    @PersistenceUnit
    override lateinit var entityManager: EntityManager

    @Inject
    lateinit var resourceGateway: ResourceGateway

    @Inject
    lateinit var resourceRepository: ResourceRepository


    @BeforeEach
    fun clearDatabase() {
        resourceRepository.deleteAll()
    }


    @Test
    fun `creates 25 entities, search for only 10`() = withUser(
        resourceEntities(25)
    ) {
        val result = resourceGateway.getResourcesByUser(
            user = it,
            params = PagedParams(0, 10)
        )
        result.content.size shouldBe 10
    }

    @Test
    fun `test if a resource is created`() {
        val content = "Inhalt"
        val name = "Test"
        val resource = resourceGateway.createResource(
            ResourceCreateDTO(
                name,
                content
            )
        )
        resource.content shouldBe content
        resource.name shouldBe name
        resourceRepository.findAll().count() shouldBe 1
    }

    @Test
    fun `test if getResources works`() = withResource {
        val result = resourceGateway.getResources(
            params = PagedParams(0, 10)
        )
        result.content.size shouldBe 1
    }

    @Test
    fun update() = withResource {
        val newName = "newName"
        val newContent = "newContent"
        val result = resourceGateway.update(
            ResourceUpdateDTO(
                it.version,
                it.id!!,
                newContent,
                newName
            )
        )

        result.name shouldBe newName
        result.content shouldBe newContent

    }

    @Test
    fun `try to to delete a resource`() = withResource {
        resourceRepository.findById(it.id!!) `should not be` null

        resourceGateway.deleteResource(it.toResourceDelete())

        resourceRepository.findById(it.id!!) `should be` null
    }


}