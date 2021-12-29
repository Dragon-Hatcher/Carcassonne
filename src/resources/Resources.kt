package resources

import game.Colors
import javax.imageio.ImageIO

object Resources {
    val MEEPLES = Colors.values().associateWith { loadImage("meeples/${it.toString().lowercase()}-meeple.png") }

    val CARD_BACK = loadImage("tiles/card-back.png")
    val CARD_BACK_INVERSE = loadImage("tiles/card-back-inverse.png")

    val LOGO = loadImage("logo/logo@32.png")
    val LOGO16 = loadImage("logo/logo@16.png")
    val LOGOS = listOf(LOGO, LOGO16)

    private fun loadImage(name: String) = ImageIO.read(Resources::class.java.getResource(name))!!
}