
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import kotlin.test.assertEquals
import org.junit.*

class DealerTestSource {
    val dealer = Dealer() 
    val strategy = PopCard()
    val numCards = 60 
    val maxCard = numCards

    val players = ArrayList<Player>() 
        
    @Before fun setUp() {
        players.add(Player("Phil H", strategy, maxCard))
        players.add(Player("Daniel N", strategy, maxCard))
        players.add(Player("Doyle B", strategy, maxCard))
    }

    @Test fun testFindRoundWinner() {
        val prizeCard = 30

        val p1 = Player("Phil H", strategy, maxCard)
        val p2 = Player("Daniel N", strategy, maxCard)
        val p3 = Player("Doyle B", strategy, maxCard)

        p1.hand = listOf(10,11).toMutableList()
        p2.hand = listOf(50,51).toMutableList()
        p3.hand = listOf(40,41).toMutableList()

        val thesePlayers = listOf(p1, p2, p3)

        // test
        val winner = dealer.findRoundWinner(prizeCard, thesePlayers)

        assertEquals(p2.name, winner.player.name)
        assertEquals(50, winner.bid.offer)
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
