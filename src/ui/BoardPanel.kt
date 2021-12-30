package ui

import game.Board
import resources.Resources
import resources.Resources.resize
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Point
import java.awt.event.*
import java.awt.geom.AffineTransform
import javax.swing.JPanel

class BoardPanel(private val board: Board) : JPanel(), MouseWheelListener, MouseListener, MouseMotionListener {
    private var offsetX = 0
    private var offsetY = 0
    private var scale = 1.0

    init {
        addMouseWheelListener(this)
        addMouseListener(this)
        addMouseMotionListener(this)
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        g as Graphics2D

        for ((pos, tile) in board.tiles) {
            val oldTransform = g.transform

            val size = (Resources.TILE_SIZE * scale).toInt()
            val screenX = offsetX + pos.x * size
            val screenY = offsetY + pos.y * size
            g.transform = AffineTransform.getRotateInstance(
                pos.rot.radians,
                screenX.toDouble() + size / 2,
                screenY.toDouble() + size / 2
            )
            g.drawImage(Resources.TILES[tile], screenX, screenY, size, size, null)

            g.transform = oldTransform
        }
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        val rot = e.wheelRotation
        if (rot == 0) return
        val sizeBefore = (Resources.TILE_SIZE * scale).toInt().toDouble()
        val mouseScreenX = (e.x - offsetX) / sizeBefore
        val mouseScreenY = (e.y - offsetY) / sizeBefore
        when {
            rot > 0 -> scale *= 0.9
            rot < 0 -> scale *= 1.1
        }
        scale = scale.coerceIn(0.2, 1.0)
        val sizeAfter = (Resources.TILE_SIZE * scale).toInt().toDouble()
        offsetX = -(sizeAfter * mouseScreenX - e.x).toInt()
        offsetY = -(sizeAfter * mouseScreenY - e.y).toInt()
        println("sb = $sizeBefore, sa = $sizeAfter, scale = $scale, e.x = ${e.x}, msx = $mouseScreenX, offsetX = $offsetX")
        repaint()
    }

    override fun mouseClicked(e: MouseEvent?) {}

    private var mouseStart: Point? = null

    override fun mousePressed(e: MouseEvent) {
        mouseStart = e.point
    }

    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    override fun mouseDragged(e: MouseEvent) {
        if (mouseStart != null) {
            offsetX += e.x - mouseStart!!.x
            offsetY += e.y - mouseStart!!.y
            repaint()
        }
        mouseStart = e.point
    }

    override fun mouseMoved(e: MouseEvent?) {}
}