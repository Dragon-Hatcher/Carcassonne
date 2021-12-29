package server

import com.drjcoding.personalserverclient.MessageManager
import com.drjcoding.personalserverclient.ServerClient
import game.PlayerColor
import game.PlayerColorList
import ui.ChooseColorScreen
import ui.carcFrame
import java.util.function.Consumer

class CarcClient : ServerClient(null) {
    lateinit var myPlayer: Player
    var playerList: PlayerList = PlayerList(listOf())
        set(value) {
            field = value
            playerListListeners.forEach { it.newPlayerList(field) }
        }

    var state = State.PLAYERS_JOINING

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
                MessageTypes.PLAYER_LIST_IS.ordinal to ::handlePlayerListIs.toConsumer(),
                MessageTypes.CHOOSE_COLOR.ordinal to ::handleChooseColor.toConsumer(),
                MessageTypes.STATE_IS.ordinal to ::handleStateIs.toConsumer(),
            )
        )
    }

    private fun handleStateIs(state: Any) {
        state as State
    }

    private fun handlePlayerListIs(playerList: Any) {
        this.playerList = playerList as PlayerList
    }

    private fun handleChooseColor(colors: Any) {
        colors as PlayerColorList
        val colorsLeft = PlayerColor.values().toSet() - colors.colors.toSet()
        carcFrame.setContentPanel(ChooseColorScreen(colorsLeft))
    }

    fun sendChosenColor(color: PlayerColor) {
        sendToMainClient(MessageTypes.MY_CHOSEN_COLOR.ordinal, color)
    }

    fun sendIConnected(player: Player) {
        myPlayer = player
        sendToMainClient(MessageTypes.NEW_PLAYER_CONNECTED.ordinal, player)
    }

    interface PlayerListListener {
        fun newPlayerList(playerList: PlayerList);
    }

    private val playerListListeners = mutableListOf<PlayerListListener>()

    fun addPlayerListListener(playerListListener: PlayerListListener) {
        playerListListeners.add(playerListListener)
    }

    fun removePlayerListListener(playerListListener: PlayerListListener) {
        playerListListeners.remove(playerListListener)
    }


    private fun <T> ((T) -> Unit).toConsumer(): Consumer<T> = Consumer<T>(this)
}