package de.ma.ikarus.impl.resources

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import de.ma.ikarus.impl.shared.ValidatedUseCase
import de.ma.ikarus.impl.shared.toDTO
import de.ma.ikarus.impl.utils.resource
import de.ma.ikarus.impl.utils.resourceCreate
import de.ma.ikarus.impl.utils.resourceShow
import de.ma.ikarus.impl.utils.user
import de.ma.ikarus.persistence.resources.ResourceGatewayImpl
import de.ma.ikarus.persistence.shared.createToEntity
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO
import de.ma.ikarus.persistence.shared.showToEntity
import de.ma.ikarus.persistence.user.UserGatewayImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.amshove.kluent.should
import org.amshove.kluent.shouldBe
import org.hibernate.validator.internal.engine.ConstraintViolationImpl
import org.hibernate.validator.internal.engine.path.PathImpl
import org.junit.jupiter.api.Test
import java.util.*
import javax.validation.ConstraintViolation
import javax.validation.Path
import javax.validation.Validator


class CreateResourceByUserUseCaseImplTest {

    private val resourceGateway = mockk<ResourceGatewayImpl>()

    private val userGateway = mockk<UserGatewayImpl>()

    private val validator = mockk<Validator>()

    private val validatedUseCase = ValidatedUseCase(validator)

    private val createResourceByUserUseCaseImpl = CreateResourceByUserUseCaseImpl(
        resourceGateway,
        userGateway,
        validatedUseCase
    )

    @Test
    fun `creates an resource with with no validation errors`() {
        val resourceCreate = resourceCreate()
        val user = user(UUID.randomUUID().toString())
        val resourceShow = resourceShow(resourceCreate.content)
        val resource = resource(content = resourceCreate.content)

        // every validation of resourceCreate should return no errors
        every { validator.validate(resourceCreate.toDTO()) } returns emptySet()

        // every resourceGateway#createResource should return resourceShow
        every { resourceGateway.createResource(any()) } returns resourceShow

        // every userGateway#addResourceToUser should return resource
        every { userGateway.addResourceToUser(user, resourceShow.showToEntity()) } returns resource


        val result = createResourceByUserUseCaseImpl(resourceCreate, user)

        if (result.exceptionOrNull() != null) {
            throw result.exceptionOrNull()!!
        }
        result `should not be` null
        result.isSuccess `should be` true
        result.getOrNull()?.content `should be` resourceCreate.content
    }

    @Test
    fun `checks if the result is a failure and throws an exception when the validator has errors`() {
        val resourceCreate = resourceCreate()
        val user = user(UUID.randomUUID().toString())
        val resourceShow = resourceShow(resourceCreate.content)
        val resource = resource(content = resourceCreate.content)

        val violation = mockk<ConstraintViolationImpl<ResourceCreateDTO>>()

        every { violation.propertyPath } returns PathImpl.createPathFromString("content")
        every { violation.message } returns "content is not valid"

        // every validation of resourceCreate should return no errors
        every { validator.validate(resourceCreate.toDTO()) } returns setOf(violation)

        val result = createResourceByUserUseCaseImpl(resourceCreate, user)

        result.isSuccess `should be` false
        result.exceptionOrNull() `should not be` null


        verify(atLeast = 1, atMost = 1) { violation.propertyPath }
        verify(atLeast = 1, atMost = 1) { violation.message }

        verify(exactly = 0) { resourceGateway.createResource(any()) }
        verify(exactly = 0) { userGateway.addResourceToUser(any(), any()) }

    }
}