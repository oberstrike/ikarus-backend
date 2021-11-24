package de.ma.ikarus.domain.shared

data class Sort(
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sort

        if (!columns.contentEquals(other.columns)) return false
        if (direction != other.direction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = columns.contentHashCode()
        result = 31 * result + direction.hashCode()
        return result
    }
}