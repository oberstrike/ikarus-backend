package de.ma.ikarus.domain.keycloak

interface GetAllKeycloakUserUseCase {
    operator fun invoke(): List<KeycloakUser>
}