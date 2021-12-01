package de.ma.ikarus.persistence.shared.nanoid

import de.ma.ikarus.domain.shared.NanoId
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class NanoIdEntity(
    override var nanoId: String = ""
) : NanoId, Serializable