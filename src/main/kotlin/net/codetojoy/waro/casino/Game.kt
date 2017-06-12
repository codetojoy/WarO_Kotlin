
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.log
import net.codetojoy.waro.logBanner

class Game(var isVerbose: Boolean) {
    val dealer = Dealer(isVerbose)

    fun playGame(numCards: Int, players: List<Player>): Player {
        val table = dealer.deal(numCards, players)

        return playGame(table)
    }

    fun playGame(table: Table): Player {

        if (isVerbose) {
            "kitty: ${table.kitty.toString()}".log()
            table.players.forEach { p ->
                "${p.name}: ${p.hand.toString()}".log()
            }
        }

        dealer.play(table)

        return determineWinner(table)
    }

    // ---- internal

    internal fun determineWinner(table: Table): Player {
        val players = table.players

        table.assertTotals()

        val tmpWinner:Player? = players.maxBy { p -> p.playerStats.total }
        val winner:Player = tmpWinner!!

        if (isVerbose) {
            players.forEach { p -> 
                val stats = p.playerStats
                "${p.name} won ${stats.numRoundsWon} rounds with ${stats.total}".log()
            }
        }

        winner.playerStats.numGamesWon++

        "".logBanner()
        "Game summary:".log()
        "overall WINNER is: ${winner.name}".log()

        return winner
    }
}
