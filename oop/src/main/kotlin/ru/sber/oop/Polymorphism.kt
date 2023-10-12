package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int get() = Random.nextInt(6)

    fun attack(opponent: Fightable): Int
    fun takeDamage(damage: Int) {
        healthPoints -= damage
    }

}

class Player(
    val name: String,
    val isBlessed: Boolean,
    override val powerType: String,
    override var healthPoints: Int,
) : Fightable {

    private val DAMAGE_MULTIPLIER = 2
    override fun attack(opponent: Fightable): Int {
        val damage = if (isBlessed) {
            damageRoll * DAMAGE_MULTIPLIER
        } else {
            damageRoll
        }
        opponent.takeDamage(damage)
        return damage
    }
}


abstract class Monster : Fightable {
    abstract val name: String
    abstract val description: String
    override fun attack(opponent: Fightable): Int {
        val damage = damageRoll
        opponent.takeDamage(damage)
        return damage
    }

}

class Goblin(
    override val name: String,
    override val powerType: String,
    override var healthPoints: Int,
    override val description: String
) : Monster() {

    override fun attack(opponent: Fightable): Int {
        return super.attack(opponent) / 2
    }
}


fun main(array: Array<String>) {
    val player: Fightable = Player("Kirill", false, "Strength", 30)
    val goblin: Fightable = Goblin("Rakanishu", "Strength", 10, "Monster")

    println(player.attack(goblin))
    println(goblin.healthPoints)
    println(goblin.attack(player))
    println(player.healthPoints)

}

fun Monster.getSalutation() = "Hello, Stranger"



