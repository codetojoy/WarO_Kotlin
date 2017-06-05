
package net.codetojoy.waro.casino

import java.util.Collections

// import com.google.common.collect.Lists

class Dealer() {

    // ------ internal 
  
    fun newDeck(numCards: Int): MutableList<Int> {
        val deck = MutableList(numCards, { it + 1 })
        Collections.shuffle(deck)
        return deck
    }

    fun getNumCardsInHand(numCards:Int, numPlayers: Int): Int {
        return (numCards / (numPlayers + 1)) 
    }    
}
