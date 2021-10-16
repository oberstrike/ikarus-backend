package de.ma.ikarus.persistence.shared

import io.quarkus.panache.common.Sort

fun de.ma.ikarus.domain.shared.Sort.toSort(): Sort {
    val targetDirection: (columns: Array<out String>) -> Sort =
        when (direction) {
            de.ma.ikarus.domain.shared.Sort.Direction.ASCENDING -> Sort::ascending
            de.ma.ikarus.domain.shared.Sort.Direction.DESCENDING -> Sort::descending
        }
    return targetDirection(columns)
}

