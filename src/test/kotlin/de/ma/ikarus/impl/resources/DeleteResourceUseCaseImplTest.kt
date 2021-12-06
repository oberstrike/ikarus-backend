package de.ma.ikarus.impl.resources

import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.shared.NotAllowedToUpdateException
import de.ma.ikarus.impl.shared.OptimisticLockException
import de.ma.ikarus.impl.shared.ResourceNotFoundException
import de.ma.ikarus.impl.shared.toNanoId
import de.ma.ikarus.impl.utils.resourceDelete
import de.ma.ikarus.impl.utils.resourceShow
import de.ma.ikarus.impl.utils.user
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

class DeleteResourceUseCaseImplTest {

    private val resourceGateway = mockk<ResourceGateway>()

    private val userGateway = mockk<UserGateway>()

    private val deleteResourceUseCase = DeleteResourceUseCaseImpl(
        resourceGateway,
        userGateway
    )

    @Test
    fun `tests if deleteResourceUseCase throws an exception when the getResourceById returns null`() {
        every { resourceGateway.getResourceById(any()) } returns null

        invoking { deleteResourceUseCase(resourceDelete(), user()) } `should throw` ResourceNotFoundException::class

        verify { resourceGateway.getResourceById(any()) }

    }

    @Test
    fun `test if deleteResourceUseCase throws an exception when the versions differ`() {
        val resourceDelete = resourceDelete(version = 1)

        every { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) } returns resourceShow(version = 0)

        invoking { deleteResourceUseCase(resourceDelete, user()) } `should throw` OptimisticLockException::class

        verify { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) }

    }


    @Test
    fun `tests if deleteResourceUseCase throws an exception when the user is now allowed`() {
        val resourceDelete = resourceDelete()
        val resourceShow = resourceShow(id = resourceDelete.id.toNanoId(), version = resourceDelete.version)

        val user = user()

        //resourceGateway#getResourceById returns a resource
        every { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) } returns resourceShow

        //userGateway#isAllowedToUpdate returns false
        every { userGateway.isAllowedToUpdate(user, resourceDelete) } returns false

        invoking {
            deleteResourceUseCase.invoke(
                resourceDelete,
                user
            )
        } `should throw` NotAllowedToUpdateException::class

        verify(exactly = 1) { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) }

        verify(exactly = 1) { userGateway.isAllowedToUpdate(user, resourceDelete) }

    }

    @Test
    fun `tests if deleteResourceUseCase deletes the resource when the user is allowed`() {
        val resourceDelete = resourceDelete()
        val user = user()

        every { userGateway.isAllowedToUpdate(user, resourceDelete) } returns true

        every{ resourceGateway.getResourceById(resourceDelete.id.toNanoId()) } returns resourceShow(version = resourceDelete.version)

        every { userGateway.removeResourceFromUser(user, resourceDelete) } returns Unit

        every { resourceGateway.deleteResource(resourceDelete) } returns Unit

        deleteResourceUseCase.invoke(resourceDelete, user)

        verify(exactly = 1) { userGateway.isAllowedToUpdate(user, resourceDelete) }

        verify(exactly = 1) { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) }

        verify(exactly = 1) { userGateway.removeResourceFromUser(user, resourceDelete) }

        verify(exactly = 1) { resourceGateway.deleteResource(resourceDelete) }
    }

    @Test
    fun `tests if deleteResourceUseCase throws an ResourceNotFoundException when the resource is not found`() {
        val resourceDelete = resourceDelete()
        val user = user()


        every { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) } throws ResourceNotFoundException(resourceDelete.id)

        invoking {
            deleteResourceUseCase.invoke(
                resourceDelete,
                user
            )
        } `should throw` ResourceNotFoundException::class


        verify(exactly = 1) { resourceGateway.getResourceById(resourceDelete.id.toNanoId()) }
    }


}