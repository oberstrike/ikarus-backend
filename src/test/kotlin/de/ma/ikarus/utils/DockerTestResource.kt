package de.ma.ikarus.utils

import de.ma.ikarus.web.utils.KeycloakContainerCreator
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class DockerTestResource : QuarkusTestResourceLifecycleManager {

    @Container
    private val listOfContainerCreator = listOf(
        KeycloakContainerCreator()
    )

    private val listOfContainer = mutableListOf<GenericContainer<*>>()


    override fun start(): MutableMap<String, String> {
        val resultConfig = mutableMapOf<String, String>()

        for (creator in listOfContainerCreator) {
            val container = creator.getContainer()

            println("Starting ${container.dockerImageName}")
            if (!container.isRunning){
                try{
                    container.start()
                }catch (e: Exception){
                    println("Container ${container.dockerImageName} failed to start")
                    throw e
                }

            }

            with(listOfContainer) {
                if (!contains(container)) add(container)
            }

            println("Started ${container.dockerImageName}")
            val config = creator.getConfig(container)
            resultConfig.putAll(config)
        }


        println("Starting with config: $resultConfig")
        return resultConfig
    }


    override fun stop() {
        listOfContainer.forEach { container ->
            println("Stopping ${container.dockerImageName}")
            if (container.isRunning) container.stop()
            println("Stopped ${container.dockerImageName}")
        }
    }


}