
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

    val p1 = Player("Phil H", strategy, maxCard)
    val p2 = Player("Daniel N", strategy, maxCard)
    val p3 = Player("Doyle B", strategy, maxCard)

    val players = mutableListOf(p1, p2, p3)

    @Test fun testPlay() {
        val kitty = Hand(listOf(35,25,55))

        p1.hand = Hand(listOf(10,11,12))
        p2.hand = Hand(listOf(15,16,17))
        p3.hand = Hand(listOf(58,50,49))

        val table = Table(players, kitty)

        // test
        dealer.play(table)

        assertEquals(0, table.players[0].numCards())
        assertEquals(0, table.players[1].numCards())
        assertEquals(0, table.players[2].numCards())
    }

    @Test fun testPlayRound() {
        val prizeCard = 42

        p1.hand = Hand(listOf(10,11,12))
        p2.hand = Hand(listOf(15,16,17))
        p3.hand = Hand(listOf(58,50,49))

        // test
        val winner = dealer.playRound(prizeCard, players)

        assertEquals("Doyle B", winner.name)
        assertEquals(1, winner.playerStats.numRoundsWon)
        assertEquals(prizeCard, winner.playerStats.total)
    }

    @Test fun testFindRoundWinner() {
        val prizeCard = 30

        p1.hand = Hand(listOf(10,11))
        p2.hand = Hand(listOf(50,51))
        p3.hand = Hand(listOf(40,41))

        // test
        val winningBid = dealer.findRoundWinner(prizeCard, players)
        val winner = winningBid.player

        assertEquals(p2.name, winner.name)
        assertEquals(50, winningBid.offer)
    }

    @Test fun deal() {
        val numPlayers = 3
        val numCardsInHand = dealer.getNumCardsInHand(numCards, numPlayers)

        // test
        val table = dealer.deal(numCards, players)

        assertEquals(numCardsInHand, table.kitty.cards.size)
        assertEquals(numPlayers, table.players.size)
        for (p in players) { assertEquals(numCardsInHand, p.numCards()) }
    }

    @Test fun testDealHands() {
        val numPlayers = 4

        // test
        val hands = dealer.dealHands(numCards, numPlayers)

        assertEquals(5, hands.size)

        var uniques = HashSet<Int>()
        hands.forEach { hand -> uniques.addAll(hand.cards) }
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
