package de.ma.ikarus.domain.resource

import de.ma.ikarus.domain.shared.HasId

interface ResourceDelete: HasId<String>{
    val version: Int
}