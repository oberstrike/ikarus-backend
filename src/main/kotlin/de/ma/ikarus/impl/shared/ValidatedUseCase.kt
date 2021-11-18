package de.ma.ikarus.impl.shared

import javax.enterprise.context.ApplicationScoped
import javax.validation.Validator

@ApplicationScoped
class ValidatedUseCase(
    private val validator: Validator
) {

    fun <T : Any> withValidated(target: Any, block: () -> T): Result<T> {
        val violations = validator.validate(target)
        if (violations.isNotEmpty()) {
            return Result.failure(ValidationException(violations))
        }
        return try {
            Result.success(block())
        } catch (e: Exception) {
            return Result.failure(e)
        }

    }


}