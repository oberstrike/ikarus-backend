package de.ma.ikarus.impl.resources.user

import de.ma.ikarus.domain.resource.ResourceGateway
import de.ma.ikarus.domain.user.UserGateway
import de.ma.ikarus.impl.resources.CreateResourceByUserUseCaseImpl
import de.ma.ikarus.impl.shared.ValidatedUseCase
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class CreateResourceByUserUseCaseImplTest {

    private val resourceGateway = mockk<ResourceGateway>()

    private val userGateway = mockk<UserGateway>()

    private val validatedUseCase = mockk<ValidatedUseCase>()

    val createResourceByUserUseCase = CreateResourceByUserUseCaseImpl(
        resourceGateway,
        userGateway,
        validatedUseCase
    )

    @Test
    operator fun invoke() {

        val resourceCreate = ResourceCreateDTO(
            "Resource1",
            "content"
        )

    }
}