
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.log
import net.codetojoy.waro.logBanner

import java.util.Collections

import com.google.common.collect.Lists

class Dealer() {
    fun deal(numCards: Int, players: List<Player>): Table {
        val numPlayers = players.size

        val hands = dealHands(numCards, numPlayers)

        val kitty = hands[0]

        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index]
        }

        val table = Table(players, kitty)

        return table
    }

    fun play(table: Table) =
        table.kitty.cards.forEach { playRound(it, table.players) }

    // ------ internal

    internal fun playRound(prizeCard: Int, players: List<Player>): Player {
        val winningBid = findRoundWinner(prizeCard, players)
        val winner = winningBid.player

        "".logBanner()
        "Round Summary:".log()
        "${winner.name} wins ${prizeCard} with ${winningBid.offer}".log()

        winner.playerStats.numRoundsWon++
        winner.playerStats.total += prizeCard

        return winner
    }

    internal fun findRoundWinner(prizeCard: Int, players: List<Player>): Bid {
        val bids = players.map { p -> p.getBid(prizeCard) }
        val result = bids.maxBy { b -> b.offer }!! 

        return result
    }

    internal fun dealHands(numCards: Int, numPlayers: Int): List<Hand> {
        var result = ArrayList<Hand>()

        val deck = newDeck(numCards)
        val numCardsInHand = getNumCardsInHand(numCards, numPlayers)

        Lists.partition(deck, numCardsInHand).forEach { hand ->
            result.add(Hand(hand))
        }

        return result
    }

    internal fun newDeck(numCards: Int): MutableList<Int> {
        val deck = MutableList(numCards, { it + 1 })
        Collections.shuffle(deck)
        return deck
    }

    internal fun getNumCardsInHand(numCards: Int, numPlayers: Int) = numCards / (numPlayers + 1)
}
