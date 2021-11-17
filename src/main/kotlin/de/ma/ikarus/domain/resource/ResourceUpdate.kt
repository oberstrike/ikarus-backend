package de.ma.ikarus.domain.resource

interface ResourceUpdate : ResourceCreate {
    val version: Int
    val id: String
}