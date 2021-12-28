package server

class PlayerList(val players: List<Player>) {

    companion object {
        fun fromObject(o: Any) = PlayerList((o as List<*>).map { player -> Player.fromObject(player!!) })
    }

    fun toObject() = players.map { it.toObject() }
}