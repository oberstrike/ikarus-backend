package de.ma.ikarus.impl.utils

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import de.ma.ikarus.domain.resource.*
import de.ma.ikarus.domain.shared.NanoId
import de.ma.ikarus.domain.user.User
import de.ma.ikarus.persistence.shared.data.*
import io.github.serpro69.kfaker.Faker


val faker = Faker()

fun resourceDelete(id: NanoId? = null, version: Int? = null): ResourceDelete {
    return faker.randomProvider.randomClassInstance<ResourceDeleteDTO> {
        typeGenerator<NanoId> { NanoIdDTO(NanoIdUtils.randomNanoId()) }
    }.let {
        it.copy(id = id?.nanoId ?: it.id, version = version ?: it.version)
    }
}

fun resourceUpdate(content: String? = null): ResourceUpdate {
    return faker.randomProvider.randomClassInstance<ResourceUpdateDTO> {
        typeGenerator<NanoId> { NanoIdDTO(NanoIdUtils.randomNanoId()) }
    }.let {
        it.copy(content = content ?: it.content)
    }
}

fun resourceCreate(content: String? = null): ResourceCreate {
    return faker.randomProvider.randomClassInstance<ResourceCreateDTO>().let {
        it.copy(content = content ?: it.content)
    }
}

fun resourceShow(content: String? = null, id: NanoId? = null, version: Int? = null): ResourceShow {
    return faker.randomProvider.randomClassInstance<ResourceShowDTO>().let {
        it.copy(content = content ?: it.content, id = id?.nanoId ?: it.id, version = version ?: it.version)
    }
}


fun resource(content: String? = null): Resource<NanoId> {
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

