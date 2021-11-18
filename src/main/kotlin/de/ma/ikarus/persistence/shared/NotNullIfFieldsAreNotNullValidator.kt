package de.ma.ikarus.persistence.shared

import javax.enterprise.context.ApplicationScoped
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.full.memberProperties

@ApplicationScoped
class NotNullIfFieldsAreNotNullValidator : ConstraintValidator<NotNullIfFieldsAreNotNull, Any> {

    lateinit var field: String

    lateinit var fieldsToCheck: Array<String>

    override fun initialize(constraintAnnotation: NotNullIfFieldsAreNotNull) {
        super.initialize(constraintAnnotation)
        this.field = constraintAnnotation.field
        this.fieldsToCheck = constraintAnnotation.fieldsToCheck
    }

    override fun isValid(value: Any, context: ConstraintValidatorContext): Boolean {

        val fieldValue = value::class.memberProperties.findLast { it.name == field } ?: throw Exception("")

        val values = value::class.memberProperties.filter { fieldsToCheck.contains(it.name) }
            .map { it.getter.call(value) }

        if (values.contains(null) && fieldValue.getter.call(value) == null) {

            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                .addPropertyNode(field)
            return false
        }

        return true;
    }
}