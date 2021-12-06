package de.ma.ikarus.jobs

import de.ma.ikarus.api.admin.GetAllUserUseCase
import de.ma.ikarus.api.user.RemoveUserUseCase
import de.ma.ikarus.domain.keycloak.GetAllKeycloakUserUseCase
import de.ma.ikarus.domain.keycloak.KeycloakUser
import de.ma.ikarus.domain.user.User
import io.mockk.every
import io.mockk.verify
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test

import javax.inject.Inject

@QuarkusTest
class UserMonitorCronJobTest {

    @Inject
    private lateinit var userMonitorCronJob: UserMonitorCronJob

    @InjectMock
    private lateinit var getAllUserUseCase: GetAllUserUseCase

    @InjectMock
    private lateinit var getAllKeycloakUserUseCase: GetAllKeycloakUserUseCase

    @InjectMock
    private lateinit var removeUserUseCase: RemoveUserUseCase

    @Test
    fun `tests if no user is deleted when no user is in the database`() {
        every { getAllUserUseCase.invoke() } returns emptyList()

        userMonitorCronJob.removeAllInactiveUsers()

        verify(exactly = 1) { getAllUserUseCase.invoke() }

        verify(exactly = 0) { getAllKeycloakUserUseCase.invoke() }

        verify(exactly = 0) { removeUserUseCase.invoke(any()) }

    }

    @Test
    fun `tests if a user is deleted when the user is in the database but not in keycloak`() {
        val id = "userId"

        val user = object : User {
            override val userId: String = id
        }

        every { getAllUserUseCase.invoke() } returns listOf(user)

        every { getAllKeycloakUserUseCase.invoke() } returns emptyList()

        every { removeUserUseCase.invoke(any()) } returns Result.success(true)

        userMonitorCronJob.removeAllInactiveUsers()

        verify(exactly = 1) { getAllUserUseCase.invoke() }

        verify(exactly = 1) { getAllKeycloakUserUseCase.invoke() }

        verify(exactly = 1) { removeUserUseCase.invoke(id) }

    }

    @Test
    fun `tests of a user is not deleted when the user is in the database but in keycloak`() {
        val id = "userId"

        val user = object : User {
            override val userId: String = id
        }

        val keycloakUser = object : KeycloakUser {
            override val userId: String = id
        }

        every { getAllUserUseCase.invoke() } returns listOf(user)

        every { getAllKeycloakUserUseCase.invoke() } returns listOf(keycloakUser)

        userMonitorCronJob.removeAllInactiveUsers()

        verify(exactly = 1) { getAllUserUseCase.invoke() }

        verify(exactly = 1) { getAllKeycloakUserUseCase.invoke() }

        verify(exactly = 0) { removeUserUseCase.invoke(id) }

    }
}