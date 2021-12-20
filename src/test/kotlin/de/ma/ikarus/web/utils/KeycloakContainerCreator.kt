package de.ma.ikarus.web.utils

import dasniko.testcontainers.keycloak.KeycloakContainer
import de.ma.ikarus.utils.IContainerCreator

class KeycloakContainerCreator : IContainerCreator<KeycloakContainer> {

    companion object {
        const val CLIENT_ID = "backend-service"
        const val CLIENT_SECRET = "secret"
        const val ADMIN_USERNAME = "admin"
        const val ADMIN_PASSWORD = "admin"
        const val REALM_IMPORT_FILE = "/testdata/realm-export.json"
        var port = 8182
        const val IMAGE_NAME = "quay.io/keycloak/keycloak:latest"
        const val REALM_NAME = "ikarus"
        var serverURL = ""
    }


    override fun getConfig(container: KeycloakContainer): MutableMap<String, String> {
        port = container.httpPort
        serverURL = container.authServerUrl

        return mutableMapOf(
            "quarkus.oidc.auth-server-url" to "${container.authServerUrl}/realms/$REALM_NAME",
            "keycloak.service.admin.serverUrl" to container.authServerUrl,
        )
    }

    override fun getContainer(): KeycloakContainer {
        return KeycloakContainer(IMAGE_NAME)
            .withAdminUsername(ADMIN_USERNAME)
            .withAdminPassword(ADMIN_PASSWORD)
            .withRealmImportFile(REALM_IMPORT_FILE)

    }

}