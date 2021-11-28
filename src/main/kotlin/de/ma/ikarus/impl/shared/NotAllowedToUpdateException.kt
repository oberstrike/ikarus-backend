package de.ma.ikarus.impl.shared

class NotAllowedToUpdateException(
    userId: String,
    resourceId: String
): RuntimeException(
    "User $userId is not allowed to update the resource $resourceId"
)