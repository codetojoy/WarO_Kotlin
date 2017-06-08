
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import kotlin.test.assertEquals
import org.junit.*

class DealerTestSource {
    val dealer = Dealer() 
    val numCards = 60 

    val players = ArrayList<Player>() 
        
    @Before fun setUp() {
        val strategy = PopCard()
        players.add(Player("Phil H", strategy, numCards))
        players.add(Player("Daniel N", strategy, numCards))
        players.add(Player("Doyle B", strategy, numCards))
    }


    @Test fun deal() {
        val numPlayers = 3
        val numCardsInHand = dealer.getNumCardsInHand(numCards, numPlayers)

        // test
        val table = dealer.deal(numCards, players)
        
        assertEquals(numCardsInHand, table.kitty.size)
        assertEquals(numPlayers, table.players.size)
        for (p in players) { assertEquals(numCardsInHand, p.hand.size) }
    }

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
