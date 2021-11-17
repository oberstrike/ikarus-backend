package de.ma.ikarus.persistence.shared

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@MappedSuperclass
abstract class AbstractNanoIdEntity : PanacheEntityBase{

    @Id
    @GeneratedValue(generator = "nano-generator")
    @GenericGenerator(
        name = "nano-generator",
        strategy = "de.ma.ikarus.persistence.shared.NanoIdGenerator"
    )
    @Column(columnDefinition = "CHAR(21)")
    var id: String? = null

    @Version
    var version: Int = 0
}