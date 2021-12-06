package de.ma.ikarus.domain.keycloak

interface KeycloakGateway {
    fun getUsers(): List<KeycloakUser>
}