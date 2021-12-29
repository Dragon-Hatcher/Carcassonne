package server

enum class State {
    PLAYERS_JOINING,
    CHOOSING_COLORS,
    PLAYING;

    companion object {
        fun fromObject(o: Any) = values()[o as Int]
    }

    fun toObject() = this.ordinal
}