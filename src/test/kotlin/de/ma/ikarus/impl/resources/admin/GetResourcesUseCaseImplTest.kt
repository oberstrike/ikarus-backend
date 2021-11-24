package de.ma.ikarus.impl.resources.admin

import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.shared.Sort
import de.ma.ikarus.shared.PagedList
import de.ma.ikarus.shared.PagedParams
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEmpty
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetResourcesUseCaseImplTest {

    private val resourceGateway = mockk<ResourceGateway>()

    private val useCase = GetResourcesUseCaseImpl(
        resourceGateway
    )

    @Test
    fun `test without any result`() {
        val pagedParams = PagedParams(0, 10)
        val sort = Sort.by(Sort.Direction.ASCENDING, "id")

        every { resourceGateway.getResources(sort, pagedParams) } returns PagedList()

        val result = useCase.invoke(pagedParams)

        result.content.shouldBeEmpty()

    }
}