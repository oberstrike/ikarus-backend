package de.ma.ikarus.impl.shared

class ResourceNotFoundException(resourceId: String) : RuntimeException(
    "Resource with id '$resourceId' not found"
)
