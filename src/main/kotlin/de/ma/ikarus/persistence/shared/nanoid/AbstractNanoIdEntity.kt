package de.ma.ikarus.persistence.shared.nanoid

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@MappedSuperclass
abstract class AbstractNanoIdEntity : PanacheEntityBase{

    @EmbeddedId
    @GeneratedValue(generator = "nano-generator")
    @GenericGenerator(
        name = "nano-generator",
        strategy = "de.ma.ikarus.persistence.shared.nanoid.NanoIdGenerator"
    )
    @Column(columnDefinition = "CHAR(21)")
    var id: NanoIdEntity? = null

    @Version
    var version: Int = 0
}