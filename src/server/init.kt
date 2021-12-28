package server

import com.drjcoding.personalserverclient.ServerClient
import com.drjcoding.personalserverclient.Translator

lateinit var client: CarcClient
var mainClient: CarcMainClient? = null

fun initServer(isMain: Boolean) {
    ServerClient.setDefaultProjectName("Carcassonne")

    Translator.registerFor(Player::class.java, Player::toObject, Player::fromObject)
    Translator.registerFor(PlayerList::class.java, PlayerList::toObject, PlayerList::fromObject)

    client = CarcClient()
    if (isMain) {
        mainClient = CarcMainClient()
        client.localMainClient = mainClient
        mainClient?.localClient = client
    }
}