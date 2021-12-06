package de.ma.ikarus.persistence.shared.nanoid

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.GeneratedValue

interface NanoIdentifiable : Serializable {
    var id: NanoIdEntity?
}