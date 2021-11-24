package de.ma.ikarus.impl.shared

import javax.validation.ConstraintViolation

class ValidationException(violations: Collection<ConstraintViolation<*>>) :
    RuntimeException(violations.groupBy(ConstraintViolation<*>::getPropertyPath){
        it.message
    }.toString())