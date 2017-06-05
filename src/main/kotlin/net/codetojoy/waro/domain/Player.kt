
package net.codetojoy.waro.domain

import net.codetojoy.waro.strategy.Strategy

class Player(val name: String, val strategy: Strategy, val maxCard:Int) {
    val playerStats: PlayerStats
    var hand: MutableList<Int>

    init {
        playerStats = PlayerStats()
        hand = mutableListOf() 
    }

    fun getBid(prizeCard:Int): Bid {
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
