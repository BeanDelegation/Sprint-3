package ru.sber.oop

open class Room(val name: String, val size: Int) {

    private val goblin: Monster = Goblin("Rakanishu", "Strength", 10, "Monster")

    constructor(name: String) : this(name, size = 100) {

    }

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = goblin.getSalutation()
}

class TownSquare(name: String, size: Int) : Room(name, size) {

    override val dangerLevel = super.dangerLevel - 3

    override fun load(): String {
        return "This is TownSquare"
    }
}

