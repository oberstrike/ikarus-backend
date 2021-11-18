package de.ma.ikarus.web.shared

import javax.validation.ConstraintViolationException
import javax.validation.Path
import javax.ws.rs.BadRequestException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ConstraintViolationExceptionHandler: ExceptionMapper<BadRequestException> {
    override fun toResponse(exception: BadRequestException): Response {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.message).build()
    }
}