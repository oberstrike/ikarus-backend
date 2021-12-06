package de.ma.ikarus.domain.shared

interface HasNanoId : HasId<NanoId> {
    override val id: NanoId
}