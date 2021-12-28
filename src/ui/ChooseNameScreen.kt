package ui

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class ChooseNameScreen: JPanel() {
    private val nameTF = JTextField().apply {
        columns = 20
    }
    private val doneB = JButton("Done")

    init {
        add(JLabel("Enter your name:"))
        add(nameTF)
        add(doneB)
    }
}