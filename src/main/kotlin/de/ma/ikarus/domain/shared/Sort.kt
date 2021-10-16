package de.ma.ikarus.domain.shared

class Sort private constructor(
    val columns: Array<out String>,
    val direction: Direction
) {


    companion object {

        @JvmStatic
        fun by(direction: Direction, vararg columns: String): Sort {
            return Sort(columns, direction)
        }
    }

    sealed class Direction {
        object ASCENDING : Direction()
        object DESCENDING : Direction()
    }
}