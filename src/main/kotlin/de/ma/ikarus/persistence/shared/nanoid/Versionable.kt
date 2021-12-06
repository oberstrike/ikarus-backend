package de.ma.ikarus.persistence.shared.nanoid

import javax.persistence.Version

interface Versionable {
    @get:Version
    var version: Int
}