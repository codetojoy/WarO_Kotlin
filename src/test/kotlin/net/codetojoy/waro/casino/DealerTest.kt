
package net.codetojoy.waro.casino

import kotlin.test.assertEquals
import org.junit.Test

class DealerTestSource {
    val dealer = Dealer() 
    val numCards = 60 

    @Test fun testDealHands() {
        val numPlayers = 4

        // test
        val hands = dealer.dealHands(numCards, numPlayers)
        
        assertEquals(5, hands.size)

        var uniques = HashSet<Int>()
        for (hand in hands) {
            for (card in hand) {
                uniques.add(card)
            }
        }
        assertEquals(numCards, uniques.size)
    }

    @Test fun testGetNumCardsInHand_Even() {
        val numPlayers = 4
        
        // test
        val result = dealer.getNumCardsInHand(numCards, numPlayers)
        
        assertEquals(12, result)
    }    

    @Test fun testGetNumCardsInHand_Odd() {
        val thisNumCards = 30 
        val numPlayers = 3
        
        // test
        val result = dealer.getNumCardsInHand(thisNumCards, numPlayers)
        
        assertEquals(7, result)
    }    

    @Test fun testNewDeckSize() {
        // test
        val deck = dealer.newDeck(numCards)

        assertEquals(numCards, deck.size)
    }

    @Test fun testNewDeckComplete() {
        // test 
        val deck = dealer.newDeck(numCards)

        var uniques = HashSet<Int>()
        for (item:Int in deck) {
            uniques.add(item)
        }
        assertEquals(numCards, uniques.size)
    }
}
