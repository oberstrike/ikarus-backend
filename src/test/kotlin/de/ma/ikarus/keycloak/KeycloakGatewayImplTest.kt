package de.ma.ikarus.keycloak

import com.google.inject.Inject
import de.ma.ikarus.domain.keycloak.KeycloakGateway
import de.ma.ikarus.persistence.resources.TransactionalQuarkusTest
import de.ma.ikarus.utils.DockerTestResource
import io.quarkus.test.common.QuarkusTestResource
import org.junit.jupiter.api.Test


@TransactionalQuarkusTest
class KeycloakGatewayImplTest {

    @Inject
    lateinit var keycloakGateway: KeycloakGateway

    @Test
    fun getUsers() {

    }
}