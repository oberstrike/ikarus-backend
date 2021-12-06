package de.ma.ikarus.web.utils

import de.ma.ikarus.persistence.utils.DatabaseConfig.username
import de.ma.ikarus.utils.DockerTestResource
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.keycloak.client.KeycloakTestClient
import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation

@QuarkusTestResource(DockerTestResource::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractWebTest {

    private val keycloakTestClient = KeycloakTestClient()

    private lateinit var keycloak: Keycloak

    @BeforeAll
    fun setup() {
        keycloak = KeycloakBuilder.builder()
            .serverUrl(KeycloakContainerCreator.serverURL)
            .realm("master")
            .username("admin")
            .password("admin")
            .clientId("admin-cli")
            .build();

        for (user in KeycloakAccountManager.registeredUsers) {
            keycloak.addUser(user)
        }

    }


    fun withUser(
        registeredKeycloakUser: RegisteredKeycloakUser,
        block: (RequestSpecification) -> Unit
    ) {
        val accessToken = keycloakTestClient.getAccessToken(
            registeredKeycloakUser.username, registeredKeycloakUser.password,
            KeycloakContainerCreator.CLIENT_ID, KeycloakContainerCreator.CLIENT_SECRET
        )

        val requestSpecBuilder = RequestSpecBuilder()
        requestSpecBuilder.addHeader("Authorization", "Bearer $accessToken")

        block(requestSpecBuilder.build())
    }

}

fun Keycloak.addUser(user: RegisteredKeycloakUser) {
    val usersResource = this.realm(KeycloakContainerCreator.REALM_NAME).users()

    val userRepresentation = UserRepresentation()
    userRepresentation.username = user.username
    userRepresentation.email = "random.$username@gmx.de"
    userRepresentation.isEmailVerified = false
    userRepresentation.isEnabled = true

    val credentialRepresentation = CredentialRepresentation()
    credentialRepresentation.isTemporary = false
    credentialRepresentation.type = CredentialRepresentation.PASSWORD
    credentialRepresentation.value = user.password
    userRepresentation.credentials = listOf(credentialRepresentation)

    val response = usersResource.create(userRepresentation)

    if (response.status != 201) {
        throw RuntimeException("Could not create user ${user.username}")
    }

    val userId = response.location.path.split("/").last()
    addRole(userId, user.role)
}

fun Keycloak.addRole(id: String, role: String) {
    val roles = this.realm(KeycloakContainerCreator.REALM_NAME).roles().list().find { it.name == role }

    this.realm(KeycloakContainerCreator.REALM_NAME).users().get(id).roles().realmLevel().add(listOf(roles))
}