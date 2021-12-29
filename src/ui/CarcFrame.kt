package ui

import resources.Resources
import javax.swing.JFrame
import javax.swing.JPanel

class CarcFrame: JFrame() {
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        iconImages = Resources.LOGOS
        title = "Carcassonne"
    }

    fun setContentPanel(panel: JPanel) {
        contentPane.removeAll()
        contentPane.add(panel)
        pack()
        if (!isVisible) {
            setLocationRelativeTo(null)
            isVisible = true
        }
    }
}