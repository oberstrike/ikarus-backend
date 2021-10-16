package de.ma.ikarus.domain.user

interface UserGateway {
    fun getUserByName(name: String): User
}