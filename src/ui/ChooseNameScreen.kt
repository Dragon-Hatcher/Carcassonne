package ui

import server.Player
import server.client
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class ChooseNameScreen: JPanel() {
    private val nameTF = JTextField().apply {
        columns = 20
    }
    private val doneB = JButton("Done").apply {
        addActionListener(::nameEntered)
    }

    init {
        add(JLabel("Enter your name:"))
        add(nameTF)
        add(doneB)
    }

    private fun nameEntered(event: ActionEvent) {
        if (nameTF.text.isEmpty()) {
            nameTF.warningLevel = WarningLevel.ERROR
            nameTF.toolTipText = "Enter a name."
        } else {
            val player = Player(client.userId, nameTF.text, null)
            client.sendIConnected(player)
            carcFrame.setContentPanel(JoinedPlayersScreen())
        }
    }
}