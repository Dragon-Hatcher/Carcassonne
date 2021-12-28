package server

import com.drjcoding.personalserverclient.MainClient
import com.drjcoding.personalserverclient.MessageManager
import java.util.function.Consumer

class CarcMainClient : MainClient(null) {
    val playerList = mutableListOf<Player>()

    init {
        manager = MessageManager(
            mapOf(
                MessageTypes.NEW_PLAYER_CONNECTED.ordinal to Player::class.java,
                MessageTypes.PLAYER_LIST_IS.ordinal to List::class.java
            ),
            mapOf(
                MessageTypes.NEW_PLAYER_CONNECTED.ordinal to ::handleNewPlayerConnected.toConsumer()
            )
        )
    }

    private fun handleNewPlayerConnected(player: Any) {
        player as Player
        playerList.add(player)
    }

    private fun <T> ((T) -> Unit).toConsumer(): Consumer<T> = Consumer<T>(this)
}