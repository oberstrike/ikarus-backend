package de.ma.ikarus.domain.resource

import de.ma.ikarus.domain.shared.HasId

interface ResourceDelete: HasId {
    val version: Int
}