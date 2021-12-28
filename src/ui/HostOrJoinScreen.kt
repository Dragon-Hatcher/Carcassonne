package ui

import server.initServer
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class HostOrJoinScreen: JPanel() {
    private val hostB = JButton("Host").apply {
        addActionListener(::host)
    }
    private val joinB = JButton("Join").apply {
        addActionListener(::join)
    }

    init {
        add(JLabel("Would you like to host a new game or join an existing one?"))
        add(hostB)
        add(joinB)
    }

    private fun host(event: ActionEvent) {
        initServer(true)
        carcFrame.setContentPanel(ChooseNameScreen())
    }

    private fun join(event: ActionEvent) {
        initServer(false)
        carcFrame.setContentPanel(ChooseNameScreen())
    }
}