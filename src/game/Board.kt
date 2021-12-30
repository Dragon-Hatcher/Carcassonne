package game

data class BoardPos(val x: Int, val y: Int)

class Board(val tiles: Map<BoardPos, Tile>) {

}