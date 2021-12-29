package resources

import game.PlayerColor
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object Resources {
    val MEEPLES = PlayerColor.values().associateWith { loadImage("meeples/${it.toString().lowercase()}-meeple.png").resize(60, 60) }

    val CARD_BACK = loadImage("tiles/card-back.png")
    val CARD_BACK_INVERSE = loadImage("tiles/card-back-inverse.png")

    val LOGO = loadImage("logo/logo@32.png")
    val LOGO16 = loadImage("logo/logo@16.png")
    val LOGOS = listOf(LOGO, LOGO16)

    private fun loadImage(name: String) = ImageIO.read(Resources::class.java.getResource(name))!!

    fun BufferedImage.resize(newW: Int, newH: Int): BufferedImage {
        val w = width
        val h = height
        val dimg = BufferedImage(newW, newH, type)
        val g = dimg.createGraphics()
        g.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        )
        g.drawImage(this, 0, 0, newW, newH, 0, 0, w, h, null)
        g.dispose()
        return dimg
    }
}