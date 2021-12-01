package de.ma.ikarus.domain.resource

import de.ma.ikarus.domain.shared.NanoId

interface Resource<T : NanoId>: ResourceCreate {
    var id: T?
    override val name: String
    override val content: String
    val version: Int
}