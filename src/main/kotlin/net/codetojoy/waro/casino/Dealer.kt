
package net.codetojoy.waro.casino

import java.util.Collections

import com.google.common.collect.Lists

class Dealer() {

    // ------ internal 

    fun dealHands(numCards:Int, numPlayers:Int): List<List<Int>> {
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

    fun getNumCardsInHand(numCards:Int, numPlayers: Int): Int {
        return (numCards / (numPlayers + 1)) 
    }    
}
