package ui

import resources.Resources
import server.*
import java.awt.event.ActionEvent
import javax.swing.*
import javax.swing.border.EtchedBorder

class JoinedPlayersScreen : JPanel(), CarcClient.PlayerListListener {
    private val everyoneJoinedB =
        (if (isMain && mainClient!!.state == State.PLAYERS_JOINING) JButton("Everyone has joined") else null)?.apply {
            addActionListener(::everyoneHasJoined)
        }

    init {
        client.addPlayerListListener(this)

        border = BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        )
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        newPlayerList(client.playerList)
    }

    override fun newPlayerList(playerList: PlayerList) {
        removeAll()
        add(JLabel("Joined Players:"))
        playerList.players.forEach {
            val label = JLabel(it.name)
            if (it.playerColor != null) label.icon = ImageIcon(Resources.MEEPLES[it.playerColor]!!)
            add(label)
        }
        if (everyoneJoinedB != null) add(everyoneJoinedB)

        carcFrame.ensureMinimumSize()
        revalidate()
    }

    private fun everyoneHasJoined(event: ActionEvent) {
        remove(everyoneJoinedB)
        mainClient!!.everyoneHasJoined()
    }
}