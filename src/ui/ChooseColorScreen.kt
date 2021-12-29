package ui

import game.PlayerColor
import resources.Resources
import server.client
import java.awt.event.ActionEvent
import javax.swing.*

class ChooseColorScreen(availablePlayerColors: Collection<PlayerColor>): JPanel() {
    private val buttons = availablePlayerColors.associateWith {
        JButton(ImageIcon(Resources.MEEPLES[it])).apply {
            addActionListener(getListener(it))
//            addActionListener(::handle)
        }
    }

    init {
        layout = BoxLayout(this, BoxLayout.X_AXIS)

        availablePlayerColors.forEach {
            add(buttons[it])
        }
    }

    private fun handle(actionEvent: ActionEvent) {
        println("button")
    }

    private fun getListener(color: PlayerColor): (ActionEvent) -> Unit {
        return { _ ->
            println("here")
            client.sendChosenColor(color)
            carcFrame.setContentPanel(JoinedPlayersScreen())
        }
    }

}