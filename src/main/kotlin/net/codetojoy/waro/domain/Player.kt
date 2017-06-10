
package net.codetojoy.waro.domain

import net.codetojoy.waro.strategy.*

class Player(val name: String, val strategy: Strategy, val maxCard: Int) {
    val playerStats: PlayerStats
    var hand: MutableList<Int>

    init {
        playerStats = PlayerStats()
        hand = mutableListOf() 
    }

    companion object {
        fun fakePlayer() : Player {
            return Player("Fake Freddie", PopCard(), 10)
        }
    }

    fun getBid(prizeCard: Int): Bid {
        val offer = strategy.selectCard(prizeCard, hand, maxCard)

        val bid = Bid(offer, this)

        hand.remove(bid.offer)
        
        return bid
    }

    fun clear() {
        hand =  mutableListOf()
        playerStats.clear()
    }
}
