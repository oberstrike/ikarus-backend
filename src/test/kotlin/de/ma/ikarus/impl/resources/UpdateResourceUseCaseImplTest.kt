package de.ma.ikarus.impl.resources

import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.utils.resourceShow
import de.ma.ikarus.impl.utils.resourceUpdate
import de.ma.ikarus.impl.utils.user
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should not be equal to`
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test


class UpdateResourceUseCaseImplTest {

    private val resourceGateway: ResourceGateway = mockk()

    private val userGateway: UserGateway = mockk()

    private val updateResourceUseCase: UpdateResourceUseCaseImpl = UpdateResourceUseCaseImpl(
        resourceGateway,
        userGateway
    )

    @Test
    fun `tests if the resource gets updated when the user is allowed to update the resource`() {
        val resourceUpdate = resourceUpdate()
        val user = user()
        val resourceShow = resourceShow(resourceUpdate.content)

        //every userGateway#isAllowedToUpdate will return true
        every { userGateway.isAllowedToUpdate(user, resourceUpdate) } returns true

        //every resourceGateway#update will return resourceUpdate
        every { resourceGateway.update(resourceUpdate) } returns resourceShow

        val result = updateResourceUseCase.invoke(resourceUpdate, user)

        result.isSuccess shouldBe true
        result.getOrNull() shouldBe resourceShow
    }

    @Test
    fun `tests if the resource doesnt get updated when the user is not allowed to update the resource`() {
        val resourceUpdate = resourceUpdate()
        val user = user()
        val resourceShow = resourceShow(resourceUpdate.content)

        //every userGateway#isAllowedToUpdate will return false
        every { userGateway.isAllowedToUpdate(user, resourceUpdate) } returns false

        //every resourceGateway#update will return resourceUpdate
        every { resourceGateway.update(resourceUpdate) } returns resourceShow

        val result = updateResourceUseCase.invoke(resourceUpdate, user)

        result.isSuccess shouldBe false
        result.getOrNull() shouldBe null
    }

    @Test
    fun `tests if a exception is thrown when the user is allowed to update but the update fails`() {
        val resourceUpdate = resourceUpdate()
        val user = user()

        //every userGateway#isAllowedToUpdate will return true
        every { userGateway.isAllowedToUpdate(user, resourceUpdate) } returns true

        //every resourceGateway#update will throw an exception
        every { resourceGateway.update(resourceUpdate) } throws Exception("test.xml")

        val result = updateResourceUseCase.invoke(resourceUpdate, user)

        result.isSuccess shouldBe false
        result.getOrNull() shouldBe null
        result.exceptionOrNull() `should not be equal to` null
        result.exceptionOrNull()?.message shouldBe "test.xml"

    }



}