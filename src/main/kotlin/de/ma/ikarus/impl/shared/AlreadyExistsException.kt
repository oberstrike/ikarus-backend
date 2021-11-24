package de.ma.ikarus.impl.shared

class AlreadyExistsException(val name: String): RuntimeException(
    "An object: $name already exists."
)