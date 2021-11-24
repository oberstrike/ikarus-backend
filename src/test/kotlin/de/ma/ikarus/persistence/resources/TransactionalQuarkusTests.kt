package de.ma.ikarus.persistence.resources

import io.quarkus.test.junit.QuarkusTest
import javax.enterprise.inject.Stereotype
import javax.transaction.Transactional

@QuarkusTest
@Stereotype
@Transactional
@Retention
@Target(AnnotationTarget.CLASS)
annotation class TransactionalQuarkusTest