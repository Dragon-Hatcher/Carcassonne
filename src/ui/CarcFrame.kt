package ui

import javax.swing.JFrame
import javax.swing.JPanel

class CarcFrame: JFrame() {
    init {
        title = "Carcassonne"
        isVisible = true
    }

    fun setContentPanel(panel: JPanel) {
        contentPane.removeAll()
        contentPane.add(panel)
        pack()
    }
}