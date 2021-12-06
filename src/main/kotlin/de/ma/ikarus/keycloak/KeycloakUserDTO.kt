package de.ma.ikarus.keycloak

import de.ma.ikarus.domain.keycloak.KeycloakUser

data class KeycloakUserDTO(
    override val userId: String
) : KeycloakUser