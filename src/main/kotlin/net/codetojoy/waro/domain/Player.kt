
package net.codetojoy.waro.domain

import net.codetojoy.waro.strategy.*

class Player(val name: String, val strategy: Strategy, val maxCard: Int) {
    val playerStats: PlayerStats
    var hand: Hand

    init {
        playerStats = PlayerStats()
        hand = Hand(listOf())
    }

    fun numCards() = hand.cards.size

    fun getBid(prizeCard: Int): Bid {
        val offer = strategy.selectCard(prizeCard, hand, maxCard)

        hand.take(offer)

        val bid = Bid(offer, this)
        
        return bid
    }

    fun clear() {
        hand.clear()
        playerStats.clear()
    }
}
