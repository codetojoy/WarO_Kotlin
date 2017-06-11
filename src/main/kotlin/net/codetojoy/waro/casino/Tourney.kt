
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.log

class Tourney(val players: List<Player>, val numGames: Int,
              val numCards: Int, val isVerbose: Boolean) {

    fun playGames() {
        for (i in 1..numGames) {
            playGame(numCards)
        }

        "\nTourney summary:  ".log()

        players.forEach { p ->
            "${p.name} has ${p.playerStats.numGamesWon} wins over ${numGames} games".log()
        }
    }

    // ------- internal

    fun playGame(numCards: Int) {
        val game = Game(isVerbose)

        game.playGame(numCards, players)

        players.forEach { it.clear() }
    }
}
