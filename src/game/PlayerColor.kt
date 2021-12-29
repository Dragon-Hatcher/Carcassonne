package game

enum class PlayerColor {
    BLACK,
    BLUE,
    GREEN,
    RED,
    YELLOW;

    companion object {
        fun fromObject(o: Any) = values()[o as Int]
    }

    fun toObject() = this.ordinal
}