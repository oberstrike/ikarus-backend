package de.ma.ikarus.domain.resource

interface ResourceShow: ResourceCreate {
    val version: Int
    val id: String
}