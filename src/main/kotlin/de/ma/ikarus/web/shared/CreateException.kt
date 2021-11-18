package de.ma.ikarus.web.shared

class CreateException(cause: Throwable?) : RuntimeException(cause?.message) {

}