package de.ma.ikarus.persistence.resources

import de.ma.ikarus.persistence.utils.AbstractRepositoryTest
import io.quarkus.test.junit.QuarkusTest
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceUnit
import javax.transaction.Transactional


@TransactionalQuarkusTest
class ResourceRepositoryTest : AbstractRepositoryTest() {

    @PersistenceUnit
    override lateinit var entityManager: EntityManager

    @Inject
    lateinit var resourceRepository: ResourceRepository

    @BeforeEach
    fun clearDatabase() {
        resourceRepository.deleteAll()
    }


    @Test
    fun `adds an resource and find it by user`() = withUser(
        resourceEntity()
    ) {
        val resources = resourceRepository.findByUser(it.userId)
        resources.size `should be` 1
    }

    @Test
    fun `saves a entity to the database`() = withResource { entity ->
        entity.id `should not be` null
        val persisted = resourceRepository.findById(entity.id!!)
        persisted `should not be` null
    }
}