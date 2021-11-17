package de.ma.ikarus.domain.resource

interface Resource: ResourceCreate {
    var id: String?
    override val name: String
    override val content: String
    val version: Int
}