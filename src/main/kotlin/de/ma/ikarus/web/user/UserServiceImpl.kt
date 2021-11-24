package de.ma.ikarus.web.user

import de.ma.ikarus.api.user.CheckIfUserExistsUseCase
import de.ma.ikarus.api.user.CreateUserUseCase
import de.ma.ikarus.domain.user.User
import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal
import io.quarkus.security.identity.SecurityIdentity
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.BadRequestException

@ApplicationScoped
class UserServiceImpl(
    private val createUserUseCase: CreateUserUseCase,
    private val checkIfUserExistsUseCase: CheckIfUserExistsUseCase
) : UserService {


    override fun <T> withUser(securityIdentity: SecurityIdentity, block: (User) -> T): T {
        val userId = (securityIdentity.principal as OidcJwtCallerPrincipal).claims.subject

        val userExists = checkIfUserExistsUseCase(userId)

        val user: User = if (!userExists) {
            val createUserResult = createUserUseCase(userId)
            createUserResult.getOrNull() ?: throw BadRequestException(
                createUserResult.exceptionOrNull()?.message ?: "No user created"
            )
        } else {
            UserForm(userId)
        }

        return block(
            user
        )
    }

}