package de.ma.ikarus.utils

import org.testcontainers.containers.GenericContainer

interface IContainerCreator<T : GenericContainer<T>?> {

    fun getContainer(): GenericContainer<T>

    fun getConfig(container: T): MutableMap<String, String>
}
