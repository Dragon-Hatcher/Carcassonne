package ui

import javax.swing.JComponent

enum class WarningLevel(val id: String) {
    NONE(""),
    WARNING("warning"),
    ERROR("error"),
}

var JComponent.warningLevel: WarningLevel
    get() = this.getClientProperty("JComponent.outline").let {
        when (it) {
            WarningLevel.WARNING.id -> WarningLevel.WARNING
            WarningLevel.ERROR.id -> WarningLevel.ERROR
            else -> WarningLevel.NONE
        }
    }
    set(type) {
        this.putClientProperty("JComponent.outline", type.id)
        this.repaint()
    }