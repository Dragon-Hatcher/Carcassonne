package game

private val FIELD = SideType.FIELD
private val CITY = SideType.CITY
private val ROAD = SideType.ROAD

enum class SideType {
    FIELD,
    CITY,
    ROAD;

    fun toFileString() = when (this) {
        FIELD -> "F"
        CITY -> "C"
        ROAD -> "R"
    }
}

private val NE = Corners.NE
private val NW = Corners.NW
private val SE = Corners.NE
private val SW = Corners.NW

enum class Corners {
    NE, NW, SE, SW
}

private fun <T> l(vararg items: T) = items.toList()

data class Tile(
    val north: SideType,
    val east: SideType,
    val south: SideType,
    val west: SideType,
    val fieldGroups: List<List<Corners>>,
    val connected: Boolean = true,
    val pendant: Boolean = false,
    val monastery: Boolean = false,
) {
    companion object {
        val START_CARD = Tile(CITY, ROAD, FIELD, ROAD, l(l(NE, NW), l(SE, SW)))
    }

    fun toFileString(): String {
        val sides = north.toFileString() + east.toFileString() + south.toFileString() + west.toFileString()
        var suffix = ""
        if (connected) suffix += "C"
        if (pendant) suffix += "P"
        if (monastery) suffix += "M"
        if (suffix.isNotEmpty()) suffix = "-$suffix"
        return sides + suffix
    }
}

class TileDeck {
    private val mutTiles: MutableList<Tile> = mutableListOf()

    val tiles: List<Tile>
        get() = mutTiles

    init {
        add(Tile(CITY, CITY, ROAD, ROAD, l(l(SE, NW), l(SW))), 3)
        add(Tile(CITY, CITY, ROAD, ROAD, l(l(SE, NW), l(SW)), pendant = true), 2)
        add(Tile(FIELD, CITY, FIELD, CITY, l(l(NE, NW), l(SE, SW))), 1)
        add(Tile(FIELD, CITY, FIELD, CITY, l(l(NE, NW), l(SE, SW)), pendant = true), 2)
        add(Tile(CITY, CITY, CITY, CITY, l(), pendant = true), 1)
        add(Tile(CITY, FIELD, FIELD, CITY, l(l(NE, SE, SW))), 3)
        add(Tile(CITY, FIELD, FIELD, CITY, l(l(NE, SE, SW)), pendant = true), 2)
        add(Tile(CITY, FIELD, FIELD, CITY, l(l(NE, SE, SW)), connected = false), 2)
        add(Tile(CITY, FIELD, CITY, FIELD, l(l(NE, NW, SE, SW)), connected = false), 3)
        add(Tile(CITY, FIELD, FIELD, FIELD, l(l(NE, NW, SE, SW))), 5)
        add(Tile(FIELD, ROAD, FIELD, ROAD, l(l(NE, NW), l(SE, SW))), 8)
        add(Tile(FIELD, FIELD, ROAD, ROAD, l(l(SW), l(NE, NW, SE))), 9)
        add(Tile(FIELD, ROAD, ROAD, ROAD, l(l(NE, NW), l(SE), l(SW)), connected = false), 4)
        add(Tile(ROAD, ROAD, ROAD, ROAD, l(l(NE), l(NW), l(SE), l(SW)), connected = false), 1)
        add(Tile(FIELD, FIELD, FIELD, FIELD, l(l(NE, NW, SE, SW)), monastery = true), 4)
        add(Tile(FIELD, FIELD, ROAD, FIELD, l(l(NE, NW, SE, SW)), monastery = true), 2)
        add(Tile(CITY, FIELD, ROAD, ROAD, l(l(SW), l(NW, NE, SE))), 3)
        add(Tile(CITY, ROAD, ROAD, FIELD, l(l(SE), l(NE, NW, SW))), 3)
        add(Tile(CITY, ROAD, FIELD, ROAD, l(l(NE, NW), l(SE, SW))), 3)
        add(Tile(CITY, ROAD, ROAD, ROAD, l(l(NE, NW), l(SE), l(SW)), connected = false), 3)
        add(Tile(CITY, CITY, FIELD, CITY, l(l(SE, SW))), 3)
        add(Tile(CITY, CITY, FIELD, CITY, l(l(SE, SW)), pendant = true), 1)
        add(Tile(CITY, CITY, ROAD, CITY, l(l(SE), l(SW))), 1)
        add(Tile(CITY, CITY, ROAD, CITY, l(l(SE), l(SW)), pendant = true), 2)
    }

    private fun add(tile: Tile, times: Int) {
        repeat(times) { mutTiles.add(tile) }
    }
}