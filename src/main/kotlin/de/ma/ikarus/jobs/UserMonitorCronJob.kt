package de.ma.ikarus.jobs

import de.ma.ikarus.api.admin.GetAllUserUseCase
import de.ma.ikarus.api.user.RemoveUserUseCase
import de.ma.ikarus.domain.keycloak.GetAllKeycloakUserUseCase
import io.quarkus.scheduler.Scheduled
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserMonitorCronJob(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val getAllKeycloakUserUseCase: GetAllKeycloakUserUseCase,
    private val removeUserUseCase: RemoveUserUseCase
) {

    companion object{
        private val LOGGER = LoggerFactory.getLogger(UserMonitorCronJob::class.java)
    }

    //scheduled job that fires at 12pm (noon) every day
    @Scheduled(cron = "0 0 12 * * ?")
    fun removeAllInactiveUsers() {
        LOGGER.info("Started removing all inactive users")
        val localUsers = getAllUserUseCase()

        if(localUsers.isEmpty()) {
            LOGGER.info("No local users found")
            return
        }

        val keycloakUsers = getAllKeycloakUserUseCase()

        for (localUser in localUsers) {
            val targetUserId = localUser.userId
            if (!keycloakUsers.map { it.userId}.contains(localUser.userId)) {
                LOGGER.info("Removing user: $targetUserId")
                val removed = removeUserUseCase(targetUserId)
                if(removed.isFailure){
                    LOGGER.error("Could not remove user: $targetUserId")
                    LOGGER.error(removed.exceptionOrNull()?.message ?: "No error message")
                    continue
                }
                LOGGER.info("Removed user: $targetUserId")
            }
        }

    }


}