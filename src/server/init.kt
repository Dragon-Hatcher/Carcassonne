package server

import com.drjcoding.personalserverclient.ServerClient
import com.drjcoding.personalserverclient.Translator
import game.PlayerColor
import game.PlayerColorList

lateinit var client: CarcClient
var mainClient: CarcMainClient? = null
var isMain = false

fun initServer(isMainIn: Boolean) {
    ServerClient.setDefaultProjectName("Carcassonne")

    Translator.registerFor(Player::class.java, Player::toObject, Player::fromObject)
    Translator.registerFor(PlayerList::class.java, PlayerList::toObject, PlayerList::fromObject)
    Translator.registerFor(PlayerColor::class.java, PlayerColor::toObject, PlayerColor::fromObject)
    Translator.registerFor(PlayerColorList::class.java, PlayerColorList::toObject, PlayerColorList::fromObject)
    Translator.registerFor(State::class.java, State::toObject, State::fromObject)

    client = CarcClient()
    isMain = isMainIn
    if (isMain) {
        mainClient = CarcMainClient()
        client.localMainClient = mainClient
        mainClient?.localClient = client
    }
}