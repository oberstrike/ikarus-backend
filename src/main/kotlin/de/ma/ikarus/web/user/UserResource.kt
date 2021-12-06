package de.ma.ikarus.web.user

import de.ma.ikarus.domain.keycloak.GetAllKeycloakUserUseCase
import de.ma.ikarus.domain.user.User
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.openapi.annotations.tags.Tags
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/api/users")
@Tags(Tag(name = "User", description = ""))
class UserResource(
    private val getAllKeycloakUserUseCase: GetAllKeycloakUserUseCase
) {

    @GET
    fun getUsers(): List<User> {
        return getAllKeycloakUserUseCase()
    }

}