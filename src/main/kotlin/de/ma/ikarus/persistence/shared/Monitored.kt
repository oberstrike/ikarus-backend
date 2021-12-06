package de.ma.ikarus.persistence.shared

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column

interface Monitored : Serializable {
    @get:CreationTimestamp
    @get:Column(name = "creation_date")
    var creationDate : LocalDateTime

    @get:UpdateTimestamp
    @get:Column(name = "update_date")
    var updateDate: LocalDateTime
}