package de.ma.ikarus.web.shared

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Constraint(validatedBy = [NotNullIfFieldsAreNotNullValidator::class])
annotation class NotNullIfFieldsAreNotNull(
    val field: String,
    val fieldsToCheck: Array<String> = [],
    val message: String = "{javax.validation.constraints.NotBlank.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
) {
    @Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class List(
        val array: Array<NotNullIfFieldsAreNotNull> = []
    )
}

