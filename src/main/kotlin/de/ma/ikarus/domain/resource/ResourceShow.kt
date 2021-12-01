package de.ma.ikarus.domain.resource

import de.ma.ikarus.domain.shared.HasId

interface ResourceShow: ResourceCreate, HasId {
    val version: Int
}