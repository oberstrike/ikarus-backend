package de.ma.ikarus.impl.keycloak

import de.ma.ikarus.domain.keycloak.GetAllKeycloakUserUseCase
import de.ma.ikarus.domain.keycloak.KeycloakGateway
import de.ma.ikarus.domain.keycloak.KeycloakUser
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GetAllKeycloakUserUseCaseImpl(
    private val keycloakGateway: KeycloakGateway
) : GetAllKeycloakUserUseCase {
    override fun invoke(): List<KeycloakUser> {
        return keycloakGateway.getUsers()
    }
}