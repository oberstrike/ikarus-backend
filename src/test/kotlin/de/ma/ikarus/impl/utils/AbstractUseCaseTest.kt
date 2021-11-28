package de.ma.ikarus.impl.utils

import de.ma.ikarus.domain.resource.Resource
import de.ma.ikarus.domain.resource.ResourceCreate
import de.ma.ikarus.domain.resource.ResourceShow
import de.ma.ikarus.domain.resource.ResourceUpdate
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.shared.data.ResourceCreateDTO
import de.ma.ikarus.persistence.shared.data.ResourceDTO
import de.ma.ikarus.persistence.shared.data.ResourceShowDTO
import de.ma.ikarus.persistence.shared.data.ResourceUpdateDTO
import io.github.serpro69.kfaker.Faker
import org.jetbrains.kotlin.konan.properties.loadProperties

val faker = Faker()

fun resourceUpdate(content: String? = null): ResourceUpdate {
    return faker.randomProvider.randomClassInstance<ResourceUpdateDTO>().let {
        it.copy(content = content ?: it.content)
    }
}

fun resourceCreate(content: String? = null): ResourceCreate {
    return faker.randomProvider.randomClassInstance<ResourceCreateDTO>().let {
        it.copy(content = content ?: it.content)
    }
}

fun resourceShow(content: String? = null): ResourceShow {
    return faker.randomProvider.randomClassInstance<ResourceShowDTO>().let {
        it.copy(content = content ?: it.content)
    }
}

fun resource(content: String? = null): Resource {
    return faker.randomProvider.randomClassInstance<ResourceDTO>().let {
        it.copy(content = content ?: it.content)
    }
}

fun user(userId: String? = null): User {
    return faker.randomProvider.randomClassInstance<UserDTO>().let {
        it.copy(userId = userId ?: it.userId)
    }
}

data class UserDTO(override val userId: String) : User
