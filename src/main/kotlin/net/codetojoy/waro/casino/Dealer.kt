
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*

import java.util.Collections

import com.google.common.collect.Lists

class Winner(val player: Player, val bid: Bid) {
    companion object {
        fun seed(): Winner {
            val fakePlayer:Player = Player.fakePlayer()
            val fakeBid:Bid = Bid(-1, fakePlayer)
            return Winner(fakePlayer, fakeBid)
        }
    }
}

class Dealer() {
    fun deal(numCards: Int, players: MutableList<Player>): Table {
        val numPlayers = players.size
        
        val hands = dealHands(numCards, numPlayers)

        val kitty = hands[0]
        
        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index].toMutableList()
        }
        
        val table = Table(players, kitty)
        
        return table
    }

    // ------ internal 

    fun findRoundWinner(prizeCard: Int, players: List<Player>): Winner {
        val seed:Winner = Winner.seed()
        
        val result:Winner = players.fold(seed) { leader, player ->
            val bid = player.getBid(prizeCard)

            if (bid.offer > leader.bid.offer) {
                Winner(bid.player, bid)
            } else {
                leader
            }
        }

        return result
    }

    fun dealHands(numCards: Int, numPlayers: Int): List<List<Int>> {
        var result = ArrayList<List<Int>>() 
        
        val deck = newDeck(numCards)        
        val numCardsInHand = getNumCardsInHand(numCards, numPlayers)

        val hands = Lists.partition(deck, numCardsInHand)

        for (hand in hands) {
            result.add(hand)
        }

        return result
    }

    fun newDeck(numCards: Int): MutableList<Int> {
        val deck = MutableList(numCards, { it + 1 })
        Collections.shuffle(deck)
        return deck
    }

    fun getNumCardsInHand(numCards: Int, numPlayers: Int): Int {
        return (numCards / (numPlayers + 1)) 
    }    
}