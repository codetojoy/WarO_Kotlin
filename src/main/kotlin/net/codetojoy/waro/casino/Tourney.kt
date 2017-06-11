
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.Log

class Tourney(val players: List<Player>, val numGames: Int,
              val numCards: Int, val isVerbose: Boolean) {

    fun playGames() {
        for (i in 1..numGames) {
            playGame(numCards)
        }

        Log.log("\nTourney summary:  ")

        for (p in players) {
            Log.log("${p.name} has ${p.playerStats.numGamesWon} wins over ${numGames} games")
        }
    }

    // ------- internal

    fun playGame(numCards: Int) {
        val game = Game(isVerbose)

        game.playGame(numCards, players)

        for (p in players) { p.clear() }
    }
}
