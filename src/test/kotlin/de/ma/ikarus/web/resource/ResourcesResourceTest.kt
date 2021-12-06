package de.ma.ikarus.web.resource

import de.ma.ikarus.web.utils.AbstractWebTest
import de.ma.ikarus.web.utils.KeycloakAccountManager
import de.ma.ikarus.web.utils.RegisteredKeycloakUser
import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.common.http.TestHTTPResource
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.security.oidc.OidcSecurity
import io.quarkus.test.security.oidc.UserInfo
import io.restassured.RestAssured.`when`
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.net.URL


@QuarkusTest
class ResourcesResourceTest : AbstractWebTest() {

    @TestHTTPEndpoint(ResourcesResource::class)
    @TestHTTPResource
    var url: URL? = null

    @Test
    fun getResources() = withUser(KeycloakAccountManager[0]) {
        Given {
            spec(it)
        }.When {
            get("/api/resources")
        }.Then {
            log().all()
            statusCode(200)
        }
    }

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