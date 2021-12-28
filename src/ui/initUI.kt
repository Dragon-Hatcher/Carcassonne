package ui

import com.formdev.flatlaf.FlatDarculaLaf
import javax.swing.SwingUtilities

lateinit var carcFrame: CarcFrame

fun initUI() {
    FlatDarculaLaf.setup()
    FlatDarculaLaf.setUseNativeWindowDecorations(true)

    SwingUtilities.invokeLater {
        carcFrame = CarcFrame()
        carcFrame.setContentPanel(HostOrJoinScreen())
    }
}