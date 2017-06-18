
package net.codetojoy.waro.domain

class Hand(var cards: List<Int>) {
    fun take(card: Int) {
        cards = cards.filter { it != card }
    }

    fun clear() {
        cards = listOf()
    }

    fun contains(card: Int) = cards.contains(card)

    override fun toString() = cards.toString() 
}
