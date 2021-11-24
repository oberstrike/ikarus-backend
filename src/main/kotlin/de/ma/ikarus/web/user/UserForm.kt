package de.ma.ikarus.web.user

import de.ma.ikarus.domain.user.User


data class UserForm(
    override val userId: String
) : User