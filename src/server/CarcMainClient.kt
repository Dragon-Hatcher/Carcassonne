package server

import com.drjcoding.personalserverclient.MainClient
import com.drjcoding.personalserverclient.MessageManager
import game.PlayerColor
import game.PlayerColorList
import java.util.function.Consumer

class CarcMainClient : MainClient(null) {
    private var playerList = PlayerList(listOf())
    var state = State.PLAYERS_JOINING
        set(value) {
            field = value
            send(MessageTypes.STATE_IS.ordinal, field, false)
        }

    init {
        manager = MessageManager(
            mapOf(
                MessageTypes.NEW_PLAYER_CONNECTED.ordinal to Player::class.java,
                MessageTypes.PLAYER_LIST_IS.ordinal to PlayerList::class.java,
                MessageTypes.CHOOSE_COLOR.ordinal to PlayerColorList::class.java,
                MessageTypes.MY_CHOSEN_COLOR.ordinal to PlayerColor::class.java,
                MessageTypes.STATE_IS.ordinal to State::class.java,
            ),
            mapOf(
                MessageTypes.NEW_PLAYER_CONNECTED.ordinal to ::handleNewPlayerConnected.toConsumer(),
                MessageTypes.MY_CHOSEN_COLOR.ordinal to ::handleChosenColor.toConsumer()
            )
        )
    }

    fun everyoneHasJoined() {
        state = State.CHOOSING_COLORS
        getNextColor()
    }

    private fun handleChosenColor(chosenColor: Any) {
        chosenColor as PlayerColor
        for (player in playerList.players) {
            if (player.playerColor == null) {
                player.playerColor = chosenColor
                println("${player.name} color is $chosenColor")
                break
            }
        }
        sendPlayersList()
        getNextColor()
    }

    private fun getNextColor() {
        val withoutColor = playerList.players.firstOrNull { it.playerColor == null }
        if (withoutColor == null) {
            state = State.PLAYING
        } else {
            val unusedColors = PlayerColorList(playerList.players.mapNotNull { it.playerColor })
            send(withoutColor.id, MessageTypes.CHOOSE_COLOR.ordinal, unusedColors)
        }
    }

    private fun handleNewPlayerConnected(player: Any) {
        if (state != State.PLAYERS_JOINING) return

        player as Player
        playerList = playerList.plus(player)
        sendPlayersList()
    }

    private fun sendPlayersList() {
        send(MessageTypes.PLAYER_LIST_IS.ordinal, playerList, false)
    }

    private fun <T> ((T) -> Unit).toConsumer(): Consumer<T> = Consumer<T>(this)
}