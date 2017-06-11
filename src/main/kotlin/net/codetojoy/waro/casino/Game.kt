
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.Log

class Game(var isVerbose: Boolean) {
    val dealer = Dealer(isVerbose)

    fun playGame(numCards: Int, players: List<Player>): Player {
        val table = dealer.deal(numCards, players)

        return playGame(table)
    }

    fun playGame(table: Table): Player {

        if (isVerbose) {
            Log.log("kitty: ${table.kitty.toString()}")
            for (p in table.players) {
                Log.log("${p.name}: ${p.hand.toString()}")
            }
        }

        dealer.play(table)

        return determineWinner(table)
    }

    // ---- internal

    fun determineWinner(table: Table): Player {
        val players = table.players

        table.assertTotals()

        val tmpWinner:Player? = players.maxBy { p -> p.playerStats.total }
        val winner:Player = tmpWinner!!

        if (isVerbose) {
            players.forEach { p -> 
                val stats = p.playerStats
                Log.log("${p.name} won ${stats.numRoundsWon} rounds with ${stats.total}")
            }
        }

        winner.playerStats.numGamesWon++
        Log.log("\nGame summary:")
        Log.log("overall WINNER is: ${winner.name}")

        return winner
    }
}
