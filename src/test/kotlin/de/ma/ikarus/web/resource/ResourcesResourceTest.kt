package de.ma.ikarus.web.resource

import de.ma.ikarus.api.resources.user.GetResourcesByUserUseCase
import de.ma.ikarus.api.user.CheckIfUserAlreadyExistsUseCase
import de.ma.ikarus.api.user.CreateUserUseCase
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.shared.PagedParams
import de.ma.ikarus.shared.emptyPagedList
import de.ma.ikarus.web.user.UserForm
import de.ma.ikarus.web.user.UserService
import de.ma.ikarus.web.utils.AbstractWebTest
import de.ma.ikarus.web.utils.KeycloakAccountManager
import de.ma.ikarus.web.utils.RegisteredKeycloakUser
import io.mockk.every
import io.mockk.verify
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.common.http.TestHTTPResource
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.security.oidc.OidcSecurity
import io.quarkus.test.security.oidc.UserInfo
import io.restassured.RestAssured.`when`
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.net.URL


@QuarkusTest
class ResourcesResourceTest : AbstractWebTest() {

    @TestHTTPEndpoint(ResourcesResource::class)
    @TestHTTPResource
    var url: URL? = null


    @InjectMock
    lateinit var createUserUseCase: CreateUserUseCase

    @InjectMock
    lateinit var checkIfUserAlreadyExistsUseCase: CheckIfUserAlreadyExistsUseCase

    @InjectMock
    lateinit var getResourcesByUserUseCase: GetResourcesByUserUseCase

    /*

    @Test
    fun `tests if no resources are present then return not found`() = withUser(KeycloakAccountManager[0]) {

        every { checkIfUserAlreadyExistsUseCase(any()) } returns false

        every { createUserUseCase(any()) } returns Result.success(UserForm("myNewUserId"))

        every { getResourcesByUserUseCase(UserForm("myNewUserId"), PagedParams()) } returns Result.failure(Exception())

        Given {
            spec(it)
        }.When {
            get(url)
        }.Then {
            log().all()
            statusCode(404)
            body("message", CoreMatchers.equalTo("No resources found"))
        }

        verify(exactly = 1) {
            checkIfUserAlreadyExistsUseCase(any())
            createUserUseCase(any())
            getResourcesByUserUseCase(UserForm("myNewUserId"), PagedParams())
        }
    }

*/

    @Test
    fun createResource() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}