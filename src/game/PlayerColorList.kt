package game

class PlayerColorList(val colors: List<PlayerColor>) {

    companion object {
        fun fromObject(o: Any) = PlayerColorList((o as List<*>).map { PlayerColor.fromObject(it!!) })
    }

    fun toObject() = colors.map { it.toObject() }
}