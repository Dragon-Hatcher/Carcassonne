package server

class Player(val name: String) {

    companion object {
        fun fromObject(o: Any) = (o as Map<*, *>).let {
            Player(it["name"]!! as String)
        }
    }

    fun toObject() = mapOf("name" to name)
}