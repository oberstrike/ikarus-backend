package de.ma.ikarus.persistence.shared.nanoid

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.GeneratedValue
import javax.persistence.MappedSuperclass


@MappedSuperclass
abstract class AbstractBaseEntity : NanoIdentifiable, PanacheEntityBase() {

    @get:EmbeddedId
    @get:GeneratedValue(generator = "nano-generator")
    @get:GenericGenerator(
        name = "nano-generator",
        strategy = "de.ma.ikarus.persistence.shared.nanoid.NanoIdGenerator"
    )
    @get:Column(columnDefinition = "CHAR(21)")
    override var id: NanoIdEntity? = null
}