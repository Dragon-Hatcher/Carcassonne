package game

import java.lang.Math.PI

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    val radians: Double
        get() = when (this) {
            NORTH -> 0.0
            EAST -> PI * 0.5
            SOUTH -> PI
            WEST -> PI * 1.5
        }
}

data class BoardPos(val x: Int, val y: Int, val rot: Direction)

class Board(val tiles: Map<BoardPos, Tile>) {

}