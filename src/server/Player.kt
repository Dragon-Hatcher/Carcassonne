package server

import game.PlayerColor

class Player(val id: String, val name: String, var playerColor: PlayerColor?) {

    companion object {
        fun fromObject(o: Any) = (o as Map<*, *>).let {
            Player(
                it["id"]!! as String,
                it["name"]!! as String,
                it["playerColor"]?.let { col -> PlayerColor.fromObject(col) })
        }
    }

    fun toObject() = mapOf(
        "id" to id,
        "name" to name,
        "playerColor" to playerColor?.toObject()
    )
}