
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.Log

import java.util.Collections

import com.google.common.collect.Lists

class Dealer(val isVerbose: Boolean) {
    fun deal(numCards: Int, players: List<Player>): Table {
        val numPlayers = players.size

        val hands = dealHands(numCards, numPlayers)

        val kitty = hands[0]

        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index].toMutableList()
        }

        val table = Table(players, kitty)

        return table
    }

    fun play(table: Table) {
        for (prizeCard in table.kitty) {
            playRound(prizeCard, table.players)
        }
    }

    // ------ internal

    fun playRound(prizeCard: Int, players: List<Player>): Player {
        val (winner, winningBid) = findRoundWinner(prizeCard, players)

        Log.log("\nthis round: ${winner.name} WINS ${prizeCard} with ${winningBid.offer}", isVerbose)

        winner.playerStats.numRoundsWon++
        winner.playerStats.total += prizeCard

        return winner
    }

    fun findRoundWinner(prizeCard: Int, players: List<Player>): Pair<Player, Bid> {
        val fakePlayer = Player.fakePlayer()
        val fakeBid = Bid(-1, fakePlayer)
        val seed = Pair(fakePlayer, fakeBid)

        val result: Pair<Player,Bid> = players.fold(seed) { leader, player ->
            val (_, highBid: Bid) = leader
            val bid = player.getBid(prizeCard)

            if (bid.offer > highBid.offer) {
                Pair(bid.player, bid)
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

    fun getNumCardsInHand(numCards: Int, numPlayers: Int) = numCards / (numPlayers + 1)
}
