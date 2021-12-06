package de.ma.ikarus.persistence.shared

import java.time.LocalDateTime

class MonitoredEntity(
    override var creationDate: LocalDateTime = LocalDateTime.now(),
    override var updateDate: LocalDateTime = LocalDateTime.now(),
) : Monitored