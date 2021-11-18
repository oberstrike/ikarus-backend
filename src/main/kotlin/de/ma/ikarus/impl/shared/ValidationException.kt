package de.ma.ikarus.impl.shared

import org.jboss.resteasy.spi.metadata.ResourceBuilder.constructor
import javax.validation.ConstraintViolation

class ValidationException(violations: Collection<ConstraintViolation<*>>) :
    RuntimeException(violations.groupBy(ConstraintViolation<*>::getPropertyPath){
        it.message
    }.toString()) {

}