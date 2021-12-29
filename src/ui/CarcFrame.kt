package ui

import resources.Resources
import java.awt.Dimension
import java.awt.Insets
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JPanel

class CarcFrame: JFrame() {
    companion object {
        val ABS_MIN = Dimension(300, 50)
    }

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        iconImages = Resources.LOGOS
        title = "Carcassonne"
        minimumSize = ABS_MIN
    }

    fun setContentPanel(panel: JPanel) {
        contentPane.removeAll()
        contentPane.add(panel)
        panel.border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), panel.border)
        pack()
        if (!isVisible) {
            setLocationRelativeTo(null)
            isVisible = true
        }
    }

    fun ensureMinimumSize() {
        fun Dimension.coerceAtLeast(other: Dimension) = Dimension(this.width.coerceAtLeast(other.width), this.height.coerceAtLeast(other.height))

        minimumSize = preferredSize.coerceAtLeast(ABS_MIN)
        size = size.coerceAtLeast(preferredSize)
    }
}