package server

import com.drjcoding.personalserverclient.MessageManager
import com.drjcoding.personalserverclient.ServerClient
import java.util.function.Consumer

class CarcClient : ServerClient(null) {
    lateinit var myPlayer: Player
    var playerList: PlayerList = PlayerList(listOf())

    init {
        manager = MessageManager(
            mapOf(
                MessageTypes.NEW_PLAYER_CONNECTED.ordinal to Player::class.java,
                MessageTypes.PLAYER_LIST_IS.ordinal to PlayerList::class.java
            ),
            mapOf(
                MessageTypes.PLAYER_LIST_IS.ordinal to ::handlePlayerListIs.toConsumer()
            )
        )
    }

    private fun handlePlayerListIs(playerList: Any) {
        this.playerList = playerList as PlayerList
    }

    fun sendIConnected(player: Player) {
        myPlayer = player
        sendToMainClient(MessageTypes.NEW_PLAYER_CONNECTED.ordinal, player)
    }

    private fun <T> ((T) -> Unit).toConsumer(): Consumer<T> = Consumer<T>(this)
}