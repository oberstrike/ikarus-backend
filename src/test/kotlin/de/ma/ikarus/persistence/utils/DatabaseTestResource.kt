package de.ma.ikarus.persistence.utils

import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import javax.inject.Inject

@Testcontainers
class DatabaseTestResource : QuarkusTestResourceLifecycleManager {

    @Container
    private lateinit var db: PostgreSQLContainer<*>


    override fun start(): Map<String, String> {
        val username = DatabaseConfig.username
        val password = DatabaseConfig.password

        db = PostgreSQLContainer<Nothing>(DatabaseConfig.container).apply {
            withDatabaseName(DatabaseConfig.database)
            withUsername(username)
            withPassword(password)
            withCreateContainerCmdModifier {
                HostConfig().withPortBindings(
                    PortBinding(
                        Ports.Binding.bindIp(DatabaseConfig.port),
                        ExposedPort(54333)
                    )
                )
            }
        }
        db.start()



        System.clearProperty("%test.xml.quarkus.hibernate-orm.dialect")
        System.setProperty("%test.xml.quarkus.datasource.jdbc.url", db.jdbcUrl)


        return mapOf(
            "quarkus.datasource.jdbc.url" to db.jdbcUrl,
            "quarkus.hibernate-orm.dialect" to "",
            "quarkus.datasource.username" to username,
            "quarkus.datasource.password" to password
        )
    }

    override fun stop() {
        db.stop()
    }


}