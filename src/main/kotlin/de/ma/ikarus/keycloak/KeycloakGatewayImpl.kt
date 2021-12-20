package de.ma.ikarus.keycloak

import de.ma.ikarus.domain.keycloak.KeycloakGateway
import de.ma.ikarus.domain.keycloak.KeycloakUser
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.UserRepresentation
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class KeycloakGatewayImpl(
    @ConfigProperty(name = "keycloak.service.admin.serverUrl")
    private val serverUrl: String,
    @ConfigProperty(name = "keycloak.service.admin.client-id")
    private val clientId: String,
    @ConfigProperty(name = "keycloak.service.admin.secret")
    private val secret: String,
    @ConfigProperty(name = "keycloak.service.admin.username")
    private val username: String,
    @ConfigProperty(name = "keycloak.service.admin.password")
    private val password: String,
    @ConfigProperty(name = "keycloak.service.admin.realm")
    private val realm: String
) : KeycloakGateway {


    private val keycloak = KeycloakBuilder.builder()
        .serverUrl(serverUrl)
        .realm(realm)
        .username(username)
        .password(password)
        .clientId(clientId)
        .clientSecret(secret)
        .build()

    override fun getUsers(): List<KeycloakUser> {
        val clients = keycloak.realm("ikarus").clients().findAll()

        return keycloak.realm("ikarus").users().list()
            .filter {   it?.clientRoles?.contains("user") ?: true }
            .map { it.toKeycloakUser() }
    }

}




fun UserRepresentation.toKeycloakUser(): KeycloakUser {
    return KeycloakUserDTO(this.id)
}